//package com.hm.HospitalManagement.Service;
//
//import com.hm.HospitalManagement.Entity.Appointment;
//import com.hm.HospitalManagement.Repository.AppointmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AppointmentService {
//
//    @Autowired
//    private AppointmentRepository appointmentRepository;
//
//    // Get all appointments for a doctor
//    public List<Appointment> getAppointmentsForDoctor(String doctorEmail) {
//        return appointmentRepository.findByDoctorEmail(doctorEmail);
//    }
//
//    // Get all appointment for the patient    //added
//    public List<Appointment> getAppointmentForPatient(String patientEmail)
//    {
//    	return appointmentRepository.findByPatientEmail(patientEmail);
//    }
//    
//    // Book a new appointment
//    public void bookAppointment(Appointment appointment) {
//        appointmentRepository.save(appointment);
//    }
//
//    // Approve or Reject an appointment
//    public void updateAppointmentStatus(Long id, String status) {
//        Optional<Appointment> appointment = appointmentRepository.findById(id);
//        if (appointment.isPresent()) {
//            appointment.get().setStatus(status);
//            appointmentRepository.save(appointment.get());
//        }
//    }
//
//    // Delete an appointment
//    public void deleteAppointment(Long id) {
//        appointmentRepository.deleteById(id);
//    }
//}


// new

package com.hm.HospitalManagement.Service;

import com.hm.HospitalManagement.Entity.Appointment;
import com.hm.HospitalManagement.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentsForDoctor(String doctorEmail) {
//        return appointmentRepository.findByDoctorEmailOrderByAppointmentDateAsc(doctorEmail);
    	return appointmentRepository.findByDoctorEmail(doctorEmail);
    }

    public void bookAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public void updateAppointmentStatus(Long id, String status) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            appointment.get().setStatus(status);
            appointmentRepository.save(appointment.get());
        }
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}