package poncha.kiet.btss6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import poncha.kiet.btss6.dto.TicketRequest;
import poncha.kiet.btss6.dto.TicketResponse;
import poncha.kiet.btss6.dto.TicketSummaryResponse;
import poncha.kiet.btss6.entity.ApiResponse;
import poncha.kiet.btss6.service.ParkingService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(name = "/api/v1/tickets")
public class ParkingTicketController {
    private final ParkingService parkingService;

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest request) {
        TicketResponse response = parkingService.checkIn(request);
        return new ResponseEntity<>(new ApiResponse<>(true, "Checked in successfully", response), HttpStatus.CREATED);
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable Long vehicleId) {
        TicketResponse response = parkingService.checkOut(vehicleId);
        return new ResponseEntity<>(new ApiResponse<>(true, "Checked in successfully", response), HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<List<TicketSummaryResponse>>> getTodaySummary() {
        List<TicketSummaryResponse> summary = parkingService.getTodaySummary();
        return new ResponseEntity<>(new ApiResponse<>(true, "Vehicles fetched successfully", summary), HttpStatus.OK);
    }
}
