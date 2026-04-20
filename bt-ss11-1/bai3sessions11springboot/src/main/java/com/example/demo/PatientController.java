package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @GetMapping("/test-log")
    public String testLog() {
        logger.info("Truy cập API test-log");
        logger.warn("Cảnh báo nhẹ");
        logger.error("Lỗi giả lập");
        return "Log đã được ghi!";
    }
}
