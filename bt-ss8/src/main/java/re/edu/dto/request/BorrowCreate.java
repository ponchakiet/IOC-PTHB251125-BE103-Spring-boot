package re.edu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import re.edu.validation.ExistingBookId;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowCreate {
    @NotBlank(message = "username không được bỏ trống")
    private String username;
    @ExistingBookId
    private Long bookId;
}
