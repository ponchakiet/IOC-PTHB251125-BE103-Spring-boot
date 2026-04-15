package re.edu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class DoctorRequest {

    @NotBlank(message = "Tên bác sĩ không được để trống")
    private String name;
    private String specialization;
    private String email;
}
