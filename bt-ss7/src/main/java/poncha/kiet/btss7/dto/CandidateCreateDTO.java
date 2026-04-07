package poncha.kiet.btss7.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateCreateDTO {
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Định dạng email không hợp lệ")
    private String email;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Ứng viên phải từ 18 tuổi trở lên")
    private Integer age;

    @NotNull(message = "Năm kinh nghiệm không được để trống")
    @Min(value = 0, message = "Năm kinh nghiệm không được là số âm")
    private Integer yearsOfExperience;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^0[35789]\\d{8}$",
            message = "Số điện thoại không hợp lệ (phải có 10 chữ số và bắt đầu bằng 03, 05, 07, 08, 09)"
    )
    private String phone;
}
