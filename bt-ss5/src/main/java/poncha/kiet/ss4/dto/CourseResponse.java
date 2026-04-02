package poncha.kiet.ss4.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import poncha.kiet.ss4.entity.CourseStatus;

@Getter
@Setter
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}
