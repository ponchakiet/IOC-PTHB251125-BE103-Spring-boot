package poncha.kiet.btss3.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss3.model.Course;
import poncha.kiet.btss3.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments;

    public EnrollmentRepository() {
        this.enrollments = new ArrayList<>();
        this.enrollments.add(new Enrollment(1, "Nguyen Tuan Kiet", 101));
        this.enrollments.add(new Enrollment(1, "Nguyen Tuan Kiet", 101));
    }

    public List<Enrollment> findAll() { return enrollments; }
}
