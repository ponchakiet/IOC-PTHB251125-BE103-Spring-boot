package poncha.kiet.btss6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import poncha.kiet.btss6.dto.VehicleCreateRequest;
import poncha.kiet.btss6.dto.VehicleResponse;
import poncha.kiet.btss6.entity.PageResponse;
import poncha.kiet.btss6.entity.Vehicle;
import poncha.kiet.btss6.entity.VehicleEnum;
import poncha.kiet.btss6.repository.IVehicleRepository;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final IVehicleRepository vehicleRepository;
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction, String keyword) {
        if (page < 0) {
            page = 0;
        }
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()) {
            if (direction != null && direction.equalsIgnoreCase("DESC")) {
                sort = Sort.by(sortBy).descending();
            } else {
                sort = Sort.by(sortBy).ascending();
            }
        }
        Pageable pageable = PageRequest.of(page,size, sort);

        Page<VehicleResponse> p = vehicleRepository.findAllByKeyword(keyword, pageable);

        return PageResponse.<VehicleResponse>builder()
                .items(p.getContent().stream().toList())
                .page(p.getNumber())
                .size(p.getSize())
                .totalItems(p.getTotalElements())
                .totalPages(p.getTotalPages())
                .isLast(p.isLast())
                .build();
    }

    public Vehicle createVehicle(VehicleCreateRequest req) {
        Vehicle vehicle = new Vehicle(null, req.getLicensePlate(), req.getColor(), req.getType(), null);
        return vehicleRepository.save(vehicle);
    }
}
