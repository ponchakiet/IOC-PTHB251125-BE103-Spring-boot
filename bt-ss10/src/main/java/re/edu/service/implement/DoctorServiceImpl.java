package re.edu.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import re.edu.dto.request.DoctorRequest;
import re.edu.dto.response.DoctorResponse;
import re.edu.entity.Doctor;
import re.edu.exception.ResourceNotFoundException;
import re.edu.repository.IDoctorRepository;
import re.edu.service.IDoctorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Override
    public Page<DoctorResponse> getAllDoctors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
        return doctorPage.map(doctor ->
                modelMapper.map(doctor, DoctorResponse.class)
        );
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        return modelMapper.map(doctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse createDoctor(DoctorRequest request) {
        if(doctorRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email Already Exists");
        }
        Doctor doctor = modelMapper.map(request,Doctor.class);
        doctorRepository.save(doctor);
        return modelMapper.map(doctor,DoctorResponse.class);
    }

    @Override
    public DoctorResponse updateDoctor(Long id, DoctorRequest request) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        doctor.setName(request.getName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setEmail(request.getEmail());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor,DoctorResponse.class);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        doctorRepository.delete(doctor);

    }
}
