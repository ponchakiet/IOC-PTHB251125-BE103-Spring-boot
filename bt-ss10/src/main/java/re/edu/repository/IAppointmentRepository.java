package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Appointment;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}
