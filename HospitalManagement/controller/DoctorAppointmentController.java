package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/appointments")
    public String showDoctorAppointments(@RequestParam String email, Model model) {
        model.addAttribute("appointments", appointmentService.getAppointmentsForDoctor(email));
        return "doctor-dashboard";
    }

    @PostMapping("/updateStatus")
    public String updateAppointmentStatus(@RequestParam Long id, @RequestParam String status) {
        appointmentService.updateAppointmentStatus(id, status);
        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(@RequestParam Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/doctor/dashboard";
    }
}
