package re.edu.service.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import re.edu.dto.PatientRequest;
import re.edu.model.Patient;
import re.edu.repository.IPatientRepository;
import re.edu.service.PatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final IPatientRepository patientRepository;
    @Override
    public Patient addPatient(PatientRequest request){
        log.info("Processing patient: {}", request.getName());

        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setAge(request.getAge());
        Patient saved = patientRepository.save(patient);

        log.info("Patient created successfully: {}", patient.getName());

        return saved;
    }

    @Override
    public List<Patient> findByName(String name) {
        log.trace("Searching patient with name: {}", name);

        List<Patient> patients = patientRepository.searchByName(name);

        log.info("Found {} patient(s)", patients.size());

        return patients;
    }
}
