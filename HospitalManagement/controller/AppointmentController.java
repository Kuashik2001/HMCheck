//package com.hm.HospitalManagement.controller;
//
//import com.hm.HospitalManagement.Entity.Appointment;
//import com.hm.HospitalManagement.Entity.Doctor;
//import com.hm.HospitalManagement.Entity.Patient;
//import com.hm.HospitalManagement.Service.AppointmentService;
//import com.hm.HospitalManagement.Service.DoctorService;
//import com.hm.HospitalManagement.Service.PatientService;
//
//import jakarta.servlet.http.HttpSession;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/appointment")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
////    @PostMapping("/book")
////    public String bookAppointment(@ModelAttribute Appointment appointment) {
////        appointmentService.bookAppointment(appointment);
////        return "redirect:/patient-dashboard";
////    }
//    //Added
//    @Autowired
//    private DoctorService doctorService;
//
//    @Autowired
//    private PatientService patientService;
//
//    @PostMapping("/book")
//    public String bookAppointment(@RequestParam Long doctorId,@RequestParam String patientName, @RequestParam String doctorEmail, @RequestParam String disease,
//                                  @RequestParam LocalDate appointmentDate, @RequestParam String bloodGroup, @RequestParam int age ,HttpSession session) {
//        String patientEmail = (String) session.getAttribute("patientEmail");
//
//        if (patientEmail == null) {
//            return "redirect:/patient/login";
//        }
////        Adding
//        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
//        if (doctor.isPresent()) {
//            Appointment appointment = new Appointment();
//            appointment.setDoctor(doctor.get());
//            appointment.setPatientName(patientName);
//            appointment.setBloodGroup(bloodGroup);
//            appointment.setAge(age);
//            appointment.setDisease(disease);
//            appointment.setAppointmentDate(appointmentDate);
//            appointment.setStatus("Pending");
//
//            appointmentService.bookAppointment(appointment);
//        }
//        return "redirect:/patient/dashboard";
//    }
//
//    @GetMapping("/doctorAppointments")
//    public String showDoctorAppointments(HttpSession session, Model model) {
//        String doctorEmail = (String) session.getAttribute("doctorEmail");
//
//        if (doctorEmail == null) {
//            return "redirect:/doctor/login";
//        }
//
//        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorEmail);
//        model.addAttribute("appointments", appointments);
//        return "doctor-dashboard";
//    }
//
//    @PostMapping("/updateStatus")
//    public String updateAppointmentStatus(@RequestParam Long id, @RequestParam String status) {
//        appointmentService.updateAppointmentStatus(id, status);
//        return "redirect:/appointment/doctorAppointments";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteAppointment(@PathVariable Long id) {
//        appointmentService.deleteAppointment(id);
//        return "redirect:/appointment/doctorAppointments";
//    }
//
//        
////        Patient patient = patientService.getPatientByEmail(patientEmail);
////        Doctor doctor = doctorService.getDoctorByEmail(doctorEmail);
////
////        if (patient != null && doctor != null) {
////            Appointment appointment = new Appointment(patient, doctor, appointmentDate, disease);
////            appointmentService.bookAppointment(appointment);
////        }
////        return "redirect:/patient/dashboard";
////    }
////
////    @GetMapping("/doctorAppointments")
////    public String showDoctorAppointments(HttpSession session, Model model) {
////        String doctorEmail = (String) session.getAttribute("doctorEmail");
////
////        if (doctorEmail == null) {
////            return "redirect:/doctor/login";
////        }
////
////        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorEmail);
////        model.addAttribute("appointments", appointments);
////        return "doctor-dashboard";
////    }
////
////    @PostMapping("/updateStatus")
////    public String updateAppointmentStatus(@RequestParam Long id, @RequestParam String status) {
////        appointmentService.updateAppointmentStatus(id, status);
////        return "redirect:/doctor/appointments";
////    }
////
////    @GetMapping("/delete/{id}")
////    public String deleteAppointment(@PathVariable Long id) {
////        appointmentService.deleteAppointment(id);
////        return "redirect:/doctor/appointments";
////    }
//}



//new

package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.Entity.Appointment;
import com.hm.HospitalManagement.Entity.Doctor;
import com.hm.HospitalManagement.Entity.Patient;
import com.hm.HospitalManagement.Service.AppointmentService;
import com.hm.HospitalManagement.Service.DoctorService;
import com.hm.HospitalManagement.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/book")
    public String bookAppointment(@RequestParam Long doctorId, @RequestParam String patientEmail,
                                  @RequestParam String disease, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate appointmentDate,
                                  HttpSession session) {
       // String patientEmail = (String) session.getAttribute("patientEmail");

        if (patientEmail == null) {
            return "redirect:/patient/login";
        }

        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
//        if (doctor.isPresent() ) {
//            Appointment appointment = new Appointment();
//            appointment.setDoctor(doctor.get());
//            appointment.setName(name);
//            appointment.setBloodGroup(bloodGroup);
//            appointment.setAge(age);
//            appointment.setDisease(disease);
//            appointment.setAppointmentDate(appointmentDate);
//            appointment.setStatus("Pending");
//
//            appointmentService.bookAppointment(appointment);
//        }
//        return "redirect:/patient/dashboard";
//    }
        
        Patient patient = patientService.getPatientByEmail(patientEmail);

        if (doctor.isPresent() && patient != null) {
            Appointment appointment = new Appointment(patient, doctor.get(), disease, appointmentDate, "Pending");
            appointmentService.bookAppointment(appointment);
        }
        return "redirect:/patient/dashboard";
    }

    @GetMapping("/doctorAppointments")
    public String showDoctorAppointments(HttpSession session, Model model) {
        String doctorEmail = (String) session.getAttribute("doctorEmail");

        if (doctorEmail == null) {
            return "redirect:/doctor/login";
        }

        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorEmail);
        model.addAttribute("appointments", appointments);
        return "doctor-dashboard";
    }

    @PostMapping("/updateStatus")
    public String updateAppointmentStatus(@RequestParam Long id, @RequestParam String status) {
        appointmentService.updateAppointmentStatus(id, status);
        return "redirect:/appointment/doctorAppointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointment/doctorAppointments";
    }
}