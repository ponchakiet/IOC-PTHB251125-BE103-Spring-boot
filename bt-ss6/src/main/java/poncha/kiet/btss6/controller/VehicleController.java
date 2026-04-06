package poncha.kiet.btss6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss6.dto.VehicleCreateRequest;
import poncha.kiet.btss6.dto.VehicleResponse;
import poncha.kiet.btss6.entity.ApiResponse;
import poncha.kiet.btss6.entity.PageResponse;
import poncha.kiet.btss6.entity.Vehicle;
import poncha.kiet.btss6.service.VehicleService;

@Controller
@RequestMapping(name = "/api/v1/vehicles/")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Vehicle>> create(@RequestBody VehicleCreateRequest req){
        vehicleService.createVehicle(req);
        return new ResponseEntity<>(new ApiResponse<>(true,"Vehicle created succesfully!"),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String sortBy,
            @RequestParam String direction,
            @RequestParam String keyword
    ){
        PageResponse<VehicleResponse> p = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        return new ResponseEntity<>(new ApiResponse<>(true, "Vehicles fetched successfully", p), HttpStatus.OK);
    }
}
