package re.edu.department.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
  @NotBlank(message = "Tên phòng ban không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải dài từ 5 đến 50 ký tự")
    private String name;
  @Size(max = 100, message = "Mô tả phải tối đa 100 kí tự")
    private String description;
}
