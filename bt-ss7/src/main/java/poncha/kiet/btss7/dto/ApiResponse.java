package poncha.kiet.btss7.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
}
