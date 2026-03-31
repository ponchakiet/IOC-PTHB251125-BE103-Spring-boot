package poncha.kiet.btss3.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss3.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private final List<Course> courses;

    public CourseRepository() {
        this.courses = new ArrayList<>();
        this.courses.add(new Course(101, "Lap trinh Java", "Active", 1));
        this.courses.add(new Course(102, "Co so du lieu", "Pending", 2));
    }

    public List<Course> findAll() { return courses; }
    public Optional<Course> findById(int id) { return courses.stream().filter(c -> c.getId() == id).findFirst(); }
    public Course create(Course c) { courses.add(c); return c; }
    public void delete(Course c) { courses.remove(c); }
}
