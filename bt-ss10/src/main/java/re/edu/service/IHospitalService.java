package re.edu.service;

import re.edu.dto.request.PrescriptionRequest;
import re.edu.dto.response.AppointmentResponse;
import re.edu.dto.response.PrescriptionResponse;
import re.edu.entity.Appointment;
import re.edu.entity.Prescription;

import java.util.List;

public interface IHospitalService {
    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);
    PrescriptionResponse getPrescription(Long patientId, Long prescriptionId);
    PrescriptionResponse createPrescription(Long patientId, PrescriptionRequest request);
}
