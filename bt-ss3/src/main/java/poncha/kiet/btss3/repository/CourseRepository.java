package poncha.kiet.btss3.repository;

import org.springframework.stereotype.Repository;
import poncha.kiet.btss3.model.Course;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses;

    public CourseRepository() {
        this.courses = new ArrayList<>();
        this.courses.add(new Course(101, "Lap trinh Java", "Active", 1));
        this.courses.add(new Course(102, "Co so du lieu", "Pending", 2));
    }

    public List<Course> findAll() { return courses; }
}
