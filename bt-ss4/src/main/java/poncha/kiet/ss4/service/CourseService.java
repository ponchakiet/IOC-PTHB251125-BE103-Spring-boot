package poncha.kiet.ss4.service;

import org.springframework.stereotype.Service;
import poncha.kiet.ss4.entity.Course;
import poncha.kiet.ss4.entity.CourseCreateRequest;
import poncha.kiet.ss4.entity.CourseUpdateRequest;
import poncha.kiet.ss4.entity.Instructor;
import poncha.kiet.ss4.repository.ICourseRepository;
import poncha.kiet.ss4.repository.IInstructorRepository;

@Service
public class CourseService {
    private final ICourseRepository courseRepository;
    private final IInstructorRepository  instructorRepository;

    public CourseService(ICourseRepository courseRepository, IInstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public Course createCourse(CourseCreateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId()).orElseThrow();
        Course course = new Course(null, req.getTitle(), req.getStatus(),instructor,null);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, CourseUpdateRequest req) {
        Instructor instructor = instructorRepository.findById(req.getInstructorId()).orElseThrow();
        Course course = courseRepository.findById(id).orElseThrow();
        course.setTitle(req.getTitle());
        course.setInstructor(instructor);
        course.setStatus(req.getStatus());
        return courseRepository.save(course);
    }
}
