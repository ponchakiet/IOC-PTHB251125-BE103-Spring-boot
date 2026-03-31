package poncha.kiet.btss3.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss3.model.Course;
import poncha.kiet.btss3.model.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments;

    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
        this.enrollments.add(new Enrollment(1, "Nguyen Tuan Kiet", 101));
        this.enrollments.add(new Enrollment(1, "Nguyen Tuan Kiet", 101));
    }

    public List<Enrollment> findAll() { return enrollments; }
    public Optional<Enrollment> findById(int id) { return enrollments.stream().filter(e -> e.getId() == id).findFirst(); }
    public Enrollment create(Enrollment e) { enrollments.add(e); return e; }
    public void delete(Enrollment e) { enrollments.remove(e); }
}
