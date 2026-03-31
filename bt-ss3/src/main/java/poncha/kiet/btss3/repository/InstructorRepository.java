package poncha.kiet.btss3.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss3.model.Instructor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors;

    public InstructorRepository() {
        this.instructors = new ArrayList<>();
        this.instructors.add(new Instructor(1, "Dr. Thanh", "thanh@ou.edu.vn"));
        this.instructors.add(new Instructor(2, "Ms. Lan", "lan@ou.edu.vn"));
    }
    public List<Instructor> findAll() { return instructors; }
}
