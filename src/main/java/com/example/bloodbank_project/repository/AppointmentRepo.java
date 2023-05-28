package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {

        @Query("SELECT a FROM Appointment a WHERE a.donor = :donor")
        List<Appointment> findDonorAppointments(@Param("donor") Donor donor);
        @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor")
        List<Appointment> findAppointmentsByDoctor(@Param("doctor") Doctor doctor);
        Appointment findAppointmentById(@Param("id") Integer id);
        void deleteById(Integer id);
        List<Appointment> findAllByDate(Date date);
        boolean existsById(Integer id);
        @Modifying
        @Transactional
        @Query("UPDATE Appointment a SET a.status = :status WHERE a.id = :id")
        void updateAppointmentStatus(@Param("id") int id, @Param("status") boolean status);
        @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor = :doctor AND a.status = :status")
        int countByDoctorAndStatus(@Param("doctor") Doctor doctor, @Param("status") boolean status);
        Page<Appointment> findByDoctor(Doctor doctor, Pageable pageable);
        @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor AND DATE(a.date) = CURRENT_DATE")
        List<Appointment> findDoctorAppointmentsToday(@Param("doctor") Doctor doctor);
        @Query("SELECT COUNT(a) FROM Appointment a WHERE DATE(a.date) = DATE(:date) AND a.donationCenter = :donationCenter")
        int getAppointmentCountByDateAndDonationCenter(@Param("date") Date date, @Param("donationCenter") DonationCenter donationCenter);
        @Query("SELECT a FROM Appointment a WHERE a.date >= :startDate AND a.date <= :endDate")
        List<Appointment> getAppointmentsByDateRange(Date startDate, Date endDate);
}
