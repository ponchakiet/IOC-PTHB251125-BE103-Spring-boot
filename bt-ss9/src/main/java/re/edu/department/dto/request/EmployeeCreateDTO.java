package re.edu.department.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateDTO {
    @NotBlank(message = "Tên nhân viên không được để trống")
    private String fullName;
    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Pattern(
            regexp = "^(03|05|07|08|09)\\d{8}$",
            message = "Số điện thoại không hợp lệ"
    )
    private String phone;

    @Min(value = 5000000, message = "Lương phải >= 5 triệu")
    private Long salary;

    @NotNull(message = "DepartmentId không được để trống")
    private Long departmentId;

}
