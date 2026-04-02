package poncha.kiet.ss4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import poncha.kiet.ss4.dto.CourseInstructorResponse;
import poncha.kiet.ss4.dto.CourseResponse;
import poncha.kiet.ss4.entity.*;
import poncha.kiet.ss4.repository.ICourseRepository;
import poncha.kiet.ss4.repository.IInstructorRepository;

import java.util.List;

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

    public PageResponse<CourseResponse> getPagedCourses(Pageable pageable) {
        if (pageable.getPageNumber() < 0)
            pageable = PageRequest.of(0, pageable.getPageSize(), pageable.getSort());
        Page<Course> page = courseRepository.findAll(pageable);
        return PageResponse.<CourseResponse>builder()
                .items(page.getContent().stream().map(this::mapToDto).toList())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }

    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, Sort.Direction direction, CourseStatus status)
    {
        Pageable pageable = PageRequest.of(page, size, direction, sortBy);
        Page<Course> coursePage = courseRepository.findAllByStatus(status, pageable);
        return PageResponse.<CourseResponse>builder()
                .items(coursePage.getContent().stream().map(this::mapToDto).toList())
                .page(coursePage.getNumber())
                .size(coursePage.getSize())
                .totalItems(coursePage.getTotalElements())
                .totalPages(coursePage.getTotalPages())
                .isLast(coursePage.isLast())
                .build();
    }

    private CourseResponse mapToDto(Course course)
    {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .instructor(new CourseInstructorResponse(course.getInstructor().getId(),
                        course.getInstructor().getName()))
                .build();
    }
}
