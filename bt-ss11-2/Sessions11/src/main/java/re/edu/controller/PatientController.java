package re.edu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.PatientRequest;
import re.edu.model.Patient;
import re.edu.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientRequest request){
        log.info("Received request to add patient: {}", request.getName());

        if (request.getAge() > 120) {
            log.warn("Invalid age detected (>120): {}", request.getAge());
        }

        Patient patient = patientService.addPatient(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }
    @GetMapping
    public List<Patient> search(@RequestParam String name) {
        log.info("Search patient with name: {}", name);
        return patientService.findByName(name);
    }

}
