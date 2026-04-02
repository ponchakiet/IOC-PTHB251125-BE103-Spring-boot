package poncha.kiet.ss4.service;

import org.springframework.stereotype.Service;
import poncha.kiet.ss4.entity.Course;
import poncha.kiet.ss4.entity.Student;
import poncha.kiet.ss4.entity.StudentEnrollment;
import poncha.kiet.ss4.repository.ICourseRepository;
import poncha.kiet.ss4.repository.IStudentEnrollmentRepository;
import poncha.kiet.ss4.repository.IStudentRepository;

@Service
public class StudentEnrollmentService {
    private final ICourseRepository courseRepository;
    private final IStudentRepository studentRepository;
    private final IStudentEnrollmentRepository studentEnrollmentRepository;

    public StudentEnrollmentService(ICourseRepository courseRepository, IStudentRepository studentRepository, IStudentEnrollmentRepository studentEnrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);
        StudentEnrollment studentEnrollment = new StudentEnrollment(null,course,student);
        return studentEnrollmentRepository.save(studentEnrollment);
    }
}
