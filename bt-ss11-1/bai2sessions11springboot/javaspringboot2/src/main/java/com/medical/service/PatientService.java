package com.medical.service;

import com.medical.model.Patient;
import com.medical.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        log.trace("[TRACE] Bắt đầu gọi getAllPatients()");
        log.debug("[DEBUG] Chuẩn bị truy vấn toàn bộ danh sách bệnh nhân");
        List<Patient> patients = patientRepository.findAll();
        log.info("[INFO]  Tìm thấy {} bệnh nhân trong database", patients.size());
        log.trace("[TRACE] Kết thúc getAllPatients() - trả về {} bản ghi", patients.size());

        return patients;
    }

    public List<Patient> searchByName(String name) {
        log.trace("[TRACE] Bắt đầu searchByName() với từ khóa='{}'", name);
        log.debug("[DEBUG] Thực thi truy vấn tìm kiếm theo tên: '{}'", name);

        List<Patient> result = patientRepository.findByNameContainingIgnoreCase(name);

        log.info("[INFO]  Tìm kiếm '{}' → {} kết quả", name, result.size());

        if (result.isEmpty()) {
            log.warn("[WARN]  Không tìm thấy bệnh nhân nào với tên '{}'", name);
        }

        log.trace("[TRACE] Kết thúc searchByName()");
        return result;
    }

    public List<Patient> searchByDiagnosis(String diagnosis) {
        log.trace("[TRACE] Bắt đầu searchByDiagnosis() với diagnosis='{}'", diagnosis);
        log.debug("[DEBUG] Thực thi truy vấn theo chẩn đoán: '{}'", diagnosis);

        List<Patient> result = patientRepository.findByDiagnosis(diagnosis);

        log.info("[INFO]  Tìm theo chẩn đoán '{}' → {} kết quả", diagnosis, result.size());
        log.trace("[TRACE] Kết thúc searchByDiagnosis()");

        return result;
    }

    public Patient addPatient(Patient patient) {
        log.trace("[TRACE] Bắt đầu addPatient() - Tên: {}", patient.getName());
        log.debug("[DEBUG] Dữ liệu sẽ INSERT: {}", patient);

        if (patient.getAge() > 120) {
            log.warn("[WARN]  Tuổi bệnh nhân {} không hợp lệ: {} tuổi", patient.getName(), patient.getAge());
        }

        Patient saved = patientRepository.save(patient);

        log.info("[INFO]  Đã thêm bệnh nhân mới - ID: {}, Tên: {}", saved.getId(), saved.getName());
        log.trace("[TRACE] Kết thúc addPatient()");

        return saved;
    }
}
