package poncha.kiet.btss3.service;

import org.springframework.stereotype.Service;
import poncha.kiet.btss3.model.Course;
import poncha.kiet.btss3.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Course> getAll() { return courseRepository.findAll(); }
    public Course getById(int id) { return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found: " + id)); }
    public Course create(Course i) { return courseRepository.create(i); }
    public Course update(int id, Course i) {
        Course existing = getById(id);
        existing.setStatus(i.getStatus());
        existing.setTitle(i.getTitle());
        existing.setInstructorId(i.getInstructorId());
        return existing;
    }
    public Course delete(int id) {
        Course existing = getById(id);
        courseRepository.delete(existing);
        return existing;
    }
}
