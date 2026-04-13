package re.edu.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.department.dto.response.ApiResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("FAIL", ex.getMessage(), null));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>("FAIL", ex.getMessage(), null));
    }
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidFile(InvalidFileException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("FAIL", ex.getMessage(), null));
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> badRequest(BadRequestException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("FAIL", ex.getMessage(), null));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("FAIL", "Dữ liệu không hợp lệ", errors));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleRuntimeException(RuntimeException ex) {
        return ApiResponse.builder()
                .status("FAIL")
                .message(ex.getMessage())
                .data(null)
                .build();
    }
}

