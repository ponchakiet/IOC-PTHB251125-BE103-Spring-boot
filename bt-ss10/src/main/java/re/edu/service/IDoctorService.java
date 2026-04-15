package re.edu.service;



import org.springframework.data.domain.Page;
import re.edu.dto.request.DoctorRequest;
import re.edu.dto.response.DoctorResponse;

public interface IDoctorService {
    Page<DoctorResponse> getAllDoctors(int page, int size);
    DoctorResponse getDoctorById(Long id);
    DoctorResponse createDoctor(DoctorRequest request);
    DoctorResponse updateDoctor(Long id, DoctorRequest request);
    void deleteDoctor(Long id);

}
