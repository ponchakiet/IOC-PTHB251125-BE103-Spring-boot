package poncha.kiet.ss4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseUpdateRequest {
    private String title;
    private CourseStatus status;
    private long instructorId;
}
