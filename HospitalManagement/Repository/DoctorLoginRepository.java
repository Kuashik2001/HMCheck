package com.hm.HospitalManagement.Repository;
import com.hm.HospitalManagement.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorLoginRepository extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String email);
}
