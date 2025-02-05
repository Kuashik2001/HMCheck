package com.hm.HospitalManagement.controller;

import com.hm.HospitalManagement.Entity.Doctor;
import com.hm.HospitalManagement.Service.DoctorLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/doctor")
public class DoctorLoginController {

    @Autowired
    private DoctorLoginService doctorLoginService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "doctor-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        Doctor doctor = doctorLoginService.findByEmail(email);

        if (doctor != null && doctor.getDateOfJoining().toString().equals(password)) {
            session.setAttribute("doctorName", doctor.getName());
            return "redirect:/doctor/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "doctor-login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model, HttpServletResponse response) {
        String doctorName = (String) session.getAttribute("doctorName");

        if (doctorName == null) {
            return "redirect:/doctor/login";
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        model.addAttribute("doctorMessage", "Welcome Dr. " + doctorName);
        return "doctor-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        return "redirect:/";
    }
}
