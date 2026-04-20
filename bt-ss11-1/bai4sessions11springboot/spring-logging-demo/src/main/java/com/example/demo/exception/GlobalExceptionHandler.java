package com.example.demo.exception;

import com.example.demo.model.ApiResponseError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponseError handleResourceNotFoundException(
            ResourceNotFoundException e,
            HttpServletRequest request) {

        log.warn("Tài nguyên không tìm thấy - Path: {} - Message: {}",
                request.getRequestURI(), e.getMessage());

        return ApiResponseError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponseError handleGenericException(
            Exception e,
            HttpServletRequest request) {

        log.error("Lỗi hệ thống xảy ra: ", e);

        return ApiResponseError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Đã xảy ra lỗi hệ thống. Vui lòng liên hệ quản trị viên.")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
