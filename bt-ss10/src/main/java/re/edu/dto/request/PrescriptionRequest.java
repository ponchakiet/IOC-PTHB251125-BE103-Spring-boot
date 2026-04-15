package re.edu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRequest {

    @NotBlank(message = "Medication is required")
    private String medication;

    @NotBlank(message = "Dosage is required")
    private String dosage;
}
