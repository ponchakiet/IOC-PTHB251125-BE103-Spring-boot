package com.medical.controller;

import com.medical.model.Patient;
import com.medical.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("GET /api/patients - Yêu cầu lấy toàn bộ danh sách bệnh nhân");
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchByName(@RequestParam String name) {
        log.info("GET /api/patients/search?name={} - Bắt đầu tìm kiếm", name);
        List<Patient> result = patientService.searchByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/diagnosis")
    public ResponseEntity<List<Patient>> searchByDiagnosis(@RequestParam String type) {
        log.info("GET /api/patients/diagnosis?type={} - Tìm theo chẩn đoán", type);
        List<Patient> result = patientService.searchByDiagnosis(type);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        log.info("POST /api/patients - Thêm bệnh nhân: {}", patient.getName());
        Patient saved = patientService.addPatient(patient);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        log.info("Health check OK");
        return ResponseEntity.ok("Server is running!");
    }
}
