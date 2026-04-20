package re.edu.service;

import re.edu.dto.PatientRequest;
import re.edu.model.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(PatientRequest request);
    List<Patient> findByName(String name);
}
