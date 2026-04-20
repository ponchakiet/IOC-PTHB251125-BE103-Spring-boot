package re.edu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientRequest {
    @NotBlank
    private String name;
    @Min(0)
    private int age;
}
