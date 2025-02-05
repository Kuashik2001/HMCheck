package com.hm.HospitalManagement.Repository;


import com.hm.HospitalManagement.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorEmail(String doctorEmail);
    //List<Appointment> findByPatientEmail(String patientEmail); // Added
}
