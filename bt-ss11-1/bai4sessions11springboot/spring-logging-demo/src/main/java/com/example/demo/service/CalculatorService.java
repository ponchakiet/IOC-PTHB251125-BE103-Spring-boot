package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculatorService {
    public double divide(double dividend, double divisor) {
        log.info("Thực hiện phép chia: {} / {}", dividend, divisor);

        if (divisor == 0) {

            throw new ArithmeticException("Không thể chia cho 0");
        }

        double result = dividend / divisor;
        log.info("Kết quả: {}", result);
        return result;
    }

    public String findUserById(Long id) {
        log.info("Tìm kiếm người dùng với ID: {}", id);

        if (id != 1L) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng với ID: " + id);
        }

        return "Nguyen Van A";
    }
}
