package poncha.kiet.btss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poncha.kiet.btss6.dto.TicketSummaryResponse;
import poncha.kiet.btss6.entity.ApiResponse;
import poncha.kiet.btss6.entity.ParkingTicket;

import java.util.List;

@Repository
public interface IParkingTicketRepository extends JpaRepository<ParkingTicket,Long> {
    public ParkingTicket findFirstByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query(value = """
        SELECT new poncha.kiet.btss6.dto.TicketSummaryResponse(
            t.id, 
            t.vehicle.licensePlate, 
            t.zone.name, 
            t.checkInTime, 
            t.checkOutTime
        )
        FROM ParkingTicket t
        WHERE CAST(t.checkInTime AS date) = CURRENT_DATE
    """)
    public List<TicketSummaryResponse> findAllTicketsToday();
}
