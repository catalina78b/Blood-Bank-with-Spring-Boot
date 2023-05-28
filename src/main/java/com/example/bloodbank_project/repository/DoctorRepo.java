package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface DoctorRepo  extends JpaRepository<Doctor, Integer> {

    Doctor findByEmail(String email);
    Doctor save(Doctor doctor);
    List<Doctor> findAll();
    @Query("SELECT u FROM Doctor u WHERE u.id = :id")
    Doctor findDoctorById(@Param("id") Integer id);
    @Modifying
    @Query("UPDATE Doctor d " +
            "set d.lastName = :lastName, d.firstName = :firstName,d.email = :email,d.password =:password, d.CNP = :CNP, d.donationCenter = :donationCenter" +
            "  where d.id = :id")
    void updateDoctor(@Param("id")int id,@Param("firstName")String firstName, @Param("lastName")String lastName,
                      @Param("email") String email,@Param("password") String password, @Param("CNP") String CNP, @Param("donationCenter") DonationCenter donationCenter);
    @Query("SELECT d FROM Doctor d WHERE d.id IN (SELECT a.doctor.id FROM Appointment a WHERE a.id = :appointmentId)")
    Doctor findDoctorByAppointmentId(int appointmentId);
}
