package re.edu.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReaderCreate {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String fullName;

    @Pattern(regexp = "^(0[0-9]{9})$", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @NotBlank
    private String address;

    private MultipartFile avatarFile;
}
