package com.hm.HospitalManagement.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.HospitalManagement.Entity.Patient;
import com.hm.HospitalManagement.Repository.PatientRepository;

@Service
public class PatientService {
	private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    
	@Autowired
    private PatientRepository patientRepository;

    // Register a new patient
    public void registerPatient(Patient patient) {
        patientRepository.save(patient);
    }

    // Validate Patient Login
    public Patient validatePatient(String email, String password) {
        Patient patient = patientRepository.findByEmail(email);
        if (patient != null && patient.getPassword().equals(password)) {
            return patient;  // Login Successful
        }
        return null;  // Login Failed
    }
    //Added
    
    //Get patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Update patient details
    public void updatePatient(Patient patient) {
        if (patientRepository.existsById(patient.getId())) {
            patientRepository.save(patient);
        }
    }

    // Delete a patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Get patient by email
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}