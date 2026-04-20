package re.edu.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.ApiResponseError;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseError handleException(Exception e) {

        log.error("Lỗi hệ thống xảy ra: ", e);

        return new ApiResponseError(
                "INTERNAL_SERVER_ERROR",
                "Có lỗi xảy ra, vui lòng thử lại sau"
        );
    }
}
