package poncha.kiet.btss6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poncha.kiet.btss6.dto.TicketRequest;
import poncha.kiet.btss6.dto.TicketResponse;
import poncha.kiet.btss6.dto.TicketSummaryResponse;
import poncha.kiet.btss6.entity.ParkingTicket;
import poncha.kiet.btss6.entity.Vehicle;
import poncha.kiet.btss6.entity.Zone;
import poncha.kiet.btss6.repository.IParkingTicketRepository;
import poncha.kiet.btss6.repository.IVehicleRepository;
import poncha.kiet.btss6.repository.IZoneRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private IParkingTicketRepository ticketRepository;
    private IVehicleRepository vehicleRepository;
    private IZoneRepository zoneRepository;

    @Transactional
    public TicketResponse checkIn(TicketRequest req) {
        Vehicle vehicle = vehicleRepository.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy xe!"));
        Zone zone = zoneRepository.findById(req.getZoneId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khu vực đỗ!"));
        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new RuntimeException("Khu vực này đã hết chỗ trống!");
        }
        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(Timestamp.valueOf(LocalDateTime.now()));
        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        zoneRepository.save(zone);
        ParkingTicket savedTicket = ticketRepository.save(ticket);

        return TicketResponse.builder()
                .id(savedTicket.getId())
                .licensePlate(vehicle.getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(savedTicket.getCheckInTime())
                .build();
    }
    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket ticket = ticketRepository.findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId);
        ticket.setCheckOutTime(Timestamp.valueOf(LocalDateTime.now()));
        Zone zone = ticket.getZone();
        zone.setOccupiedSpots(Math.max(0, zone.getOccupiedSpots() - 1));
        zoneRepository.save(zone);
        ticketRepository.save(ticket);
        return TicketResponse.builder()
                .id(ticket.getId())
                .licensePlate(ticket.getVehicle().getLicensePlate())
                .zoneName(zone.getName())
                .checkInTime(ticket.getCheckInTime())
                .checkOutTime(ticket.getCheckOutTime())
                .build();
    }

    public List<TicketSummaryResponse> getTodaySummary() {
        return ticketRepository.findAllTicketsToday();
    }
}
