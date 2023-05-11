package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

        @Query("SELECT a FROM Appointment a WHERE a.donor = :donor")
        List<Appointment> findDonorAppointments(@Param("donor") Donor donor);
        @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor")
        List<Appointment> getAppointmentsByDoctor(@Param("doctor") Doctor doctor);
        Appointment findAppointmentById(@Param("id") Integer id);
}
