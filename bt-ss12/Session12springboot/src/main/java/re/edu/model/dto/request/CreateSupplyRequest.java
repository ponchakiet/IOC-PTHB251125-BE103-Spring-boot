package re.edu.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupplyRequest {

    @NotBlank(message = "Tên vật tư không được để trống")
    private String name;
    private String specification;
    private String provider;
    @NotBlank(message = "Đơn vị tính không được để trống")
    private String unit;
}
