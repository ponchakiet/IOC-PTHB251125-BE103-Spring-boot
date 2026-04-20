package com.example.demo.controller;

import com.example.demo.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

    private final CalculatorService calculatorService;

    @GetMapping("/divide")
    public ResponseEntity<Double> divide(
            @RequestParam double dividend,
            @RequestParam double divisor) {

        log.info("Nhận yêu cầu chia: {} / {}", dividend, divisor);
        double result = calculatorService.divide(dividend, divisor);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        log.info("Nhận yêu cầu lấy thông tin user ID: {}", id);
        String user = calculatorService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/system-error")
    public ResponseEntity<String> triggerSystemError() {
        log.info("Nhận yêu cầu gây lỗi hệ thống demo");

        String text = null;
        return ResponseEntity.ok(text.toUpperCase());
    }
}
