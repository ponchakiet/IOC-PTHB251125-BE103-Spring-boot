package com.example.patientlogging.controller;

import com.example.patientlogging.model.Patient;
import com.example.patientlogging.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {

        log.info("===== Nhận request POST /api/patients =====");
        log.info("Thông tin bệnh nhân từ client: Tên={}, Tuổi={}", patient.getName(), patient.getAge());

        String result = patientService.addPatient(patient);

        log.info("Phản hồi trả về client: {}", result);
        log.info("===== Kết thúc xử lý request =====");

        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("Health check được gọi - Server đang hoạt động bình thường");
        return ResponseEntity.ok("Server is running!");
    }
}
