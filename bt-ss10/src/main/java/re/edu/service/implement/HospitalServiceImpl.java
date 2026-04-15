package re.edu.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import re.edu.dto.request.PrescriptionRequest;
import re.edu.dto.response.AppointmentResponse;
import re.edu.dto.response.PrescriptionResponse;
import re.edu.entity.Patient;
import re.edu.entity.Prescription;
import re.edu.exception.ResourceNotFoundException;
import re.edu.repository.IAppointmentRepository;
import re.edu.repository.IDoctorRepository;
import re.edu.repository.IPatientRepository;
import re.edu.repository.IPrescriptionRepository;
import re.edu.service.IHospitalService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private final IPatientRepository patientRepository;
    private final IPrescriptionRepository prescriptionRepository;
    private final IDoctorRepository doctorRepository;
    private final IAppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;



    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId) {

        if (!doctorRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(a -> modelMapper.map(a, AppointmentResponse.class))
                .toList();
    }

    @Override
    public PrescriptionResponse getPrescription(Long patientId, Long prescriptionId) {

        Prescription prescription = prescriptionRepository
                .findByIdAndPatientId(prescriptionId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found"));

        return modelMapper.map(prescription, PrescriptionResponse.class);
    }


    @Override
    public PrescriptionResponse createPrescription(Long patientId, PrescriptionRequest request) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Prescription prescription = modelMapper.map(request, Prescription.class);
        prescription.setPatient(patient);
        Prescription saved = prescriptionRepository.save(prescription);
        return modelMapper.map(saved, PrescriptionResponse.class);
    }
}
