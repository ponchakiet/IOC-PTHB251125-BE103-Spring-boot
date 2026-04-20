package com.example.patientlogging.service;

import com.example.patientlogging.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service xử lý logic nghiệp vụ cho bệnh nhân
 *
 * @Slf4j: Lombok tự động tạo biến log
 *   => private static final Logger log = LoggerFactory.getLogger(PatientService.class);
 */
@Slf4j
@Service
public class PatientService {

    private static final int MAX_VALID_AGE = 120;
    public String addPatient(Patient patient) {

        log.info("Đang xử lý thêm bệnh nhân: [Tên: {}, Tuổi: {}, Chẩn đoán: {}]",
                patient.getName(), patient.getAge(), patient.getDiagnosis());

        if (patient.getAge() > MAX_VALID_AGE) {
            log.warn("CẢNH BÁO - Tuổi bệnh nhân '{}' không hợp lệ: {} tuổi (vượt quá giới hạn {} tuổi)",
                    patient.getName(), patient.getAge(), MAX_VALID_AGE);
        }


        log.info("Bệnh nhân '{}' đã được thêm thành công vào hệ thống.", patient.getName());

        return "Thêm bệnh nhân thành công: " + patient.getName();
    }
}
