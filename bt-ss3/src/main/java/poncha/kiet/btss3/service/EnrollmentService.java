package poncha.kiet.btss3.service;

import org.springframework.stereotype.Service;
import poncha.kiet.btss3.model.Enrollment;
import poncha.kiet.btss3.repository.EnrollmentRepository;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAll() { return enrollmentRepository.findAll(); }
    public Enrollment getById(int id) { return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found: " + id)); }
    public Enrollment create(Enrollment i) { return enrollmentRepository.create(i); }
    public Enrollment update(int id, Enrollment i) {
        Enrollment existing = getById(id);
        existing.setCourseId(i.getCourseId());
        existing.setStudentName(i.getStudentName());
        return existing;
    }
    public Enrollment delete(int id) {
        Enrollment existing = getById(id);
        enrollmentRepository.delete(existing);
        return existing;
    }
}
