package re.edu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.request.PrescriptionRequest;
import re.edu.dto.response.ApiResponse;
import re.edu.dto.response.AppointmentResponse;
import re.edu.dto.response.PrescriptionResponse;
import re.edu.service.IHospitalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Hospital API", description = "Quản lý lịch hẹn & đơn thuốc")
public class HospitalController {

    private final IHospitalService hospitalService;

    @Operation(summary = "Lấy tất cả lịch hẹn của bác sĩ")
    @GetMapping("/doctors/{doctorId}/appointments")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAppointments(
            @PathVariable Long doctorId
    ) {
        return ResponseEntity.ok(
                ApiResponse.<List<AppointmentResponse>>builder()
                        .status("success")
                        .code(200)
                        .data(hospitalService.getAppointmentsByDoctor(doctorId))
                        .build()
        );
    }

    @Operation(summary = "Lấy đơn thuốc theo patientId và prescriptionId")
    @GetMapping("/patients/{patientId}/prescriptions/{prescriptionId}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> getPrescription(
            @PathVariable Long patientId,
            @PathVariable Long prescriptionId
    ) {
        return ResponseEntity.ok(
                ApiResponse.<PrescriptionResponse>builder()
                        .status("success")
                        .code(200)
                        .data(hospitalService.getPrescription(patientId, prescriptionId))
                        .build()
        );
    }

    @Operation(summary = "Tạo đơn thuốc cho bệnh nhân")
    @PostMapping("/patients/{patientId}/prescriptions")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> createPrescription(
            @PathVariable Long patientId,
            @Valid @RequestBody PrescriptionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<PrescriptionResponse>builder()
                        .status("success")
                        .code(201)
                        .data(hospitalService.createPrescription(patientId, request))
                        .build()
        );
    }
}
