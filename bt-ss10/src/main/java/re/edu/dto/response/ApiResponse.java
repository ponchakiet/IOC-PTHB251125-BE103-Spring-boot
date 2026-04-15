package re.edu.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private String status;
    private int code;
    private T data;
    private Meta meta;
    public static <T> ApiResponse<T> success(T data, int code) {
        return ApiResponse.<T>builder()
                .status("success")
                .code(code)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data, Meta meta, int code) {
        return ApiResponse.<T>builder()
                .status("success")
                .code(code)
                .data(data)
                .meta(meta)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, int code) {
        return ApiResponse.<T>builder()
                .status("error")
                .code(code)
                .data((T) message)
                .build();
    }
}