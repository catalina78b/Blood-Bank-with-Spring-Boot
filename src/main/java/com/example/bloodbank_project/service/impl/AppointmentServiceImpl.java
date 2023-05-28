package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.*;
import com.example.bloodbank_project.lottery.Lottery;
import com.example.bloodbank_project.repository.AppointmentRepo;
import com.example.bloodbank_project.repository.DonationCenterRepo;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.text.SimpleDateFormat;


@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DonationCenterRepo donationCenterRepo;

    public List<Appointment> findDonorAppointments(Donor donor){
        return appointmentRepo.findDonorAppointments(donor);
    }

    @Override
    public void saveAppointment(AppointmentDTO appointmentDTO) {
        System.out.println(appointmentDTO.getDate().toString());
        Appointment appointment = new Appointment();

        // Convert the date to LocalDateTime
        Date date = appointmentDTO.getDate();
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        // Convert the LocalDateTime to UTC timezone
        ZoneId utcZone = ZoneId.of("UTC");
        LocalDateTime utcDateTime = dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(utcZone).toLocalDateTime();

        // Format the LocalDateTime as a string
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String dateString = utcDateTime.format(formatter);

        // Parse the string to LocalDateTime
        LocalDateTime parsedDateTime = LocalDateTime.parse(dateString, formatter);

        // Convert the LocalDateTime to Date in UTC timezone
        Date utcDate = Date.from(parsedDateTime.atZone(utcZone).toInstant());

        Donor donor=userService.findDonorById(appointmentDTO.getDonorId());
        System.out.println(donor.getChances());
        donor.setChances(donor.getChances()+1);
        System.out.println(donor.getChances());

        appointment.setDate(utcDate);
        appointment.setDonor(donor);
        appointment.setDonationCenter(donationCenterRepo.findDonationCenterById(appointmentDTO.getDonationCenter().getId()));
        appointment.setStatus(false);
        appointment.setNotification(appointmentDTO.getNotification());


        System.out.println(appointmentDTO.getNotification());

        System.out.println(appointmentDTO.getDonationCenter().getCenterName());
        Doctor doctor = userService.findDoctorWithMinAppointments(appointmentDTO.getDonationCenter());
        appointment.setDoctor(doctor);

        appointmentRepo.save(appointment);
    }


    @Override
    public List<Appointment> findAllDoctorAppointments(Doctor doctor){
        return appointmentRepo.findAppointmentsByDoctor(doctor);
    }

    @Override
    public Appointment findById(int id) {
        return appointmentRepo.findAppointmentById(id);
    }
    @Override
    public void updateAppointment(int id,boolean status)
    {
        Appointment appointment=appointmentRepo.findAppointmentById(id);
        if(appointment!=null)
            appointmentRepo.updateAppointmentStatus(id,status);
    }

    @Override
    public void deleteAppointmentById(int id) {
        Appointment appointment=appointmentRepo.findAppointmentById(id);
        if(appointment!=null)
        {
            Donor donor=userService.findDonorById(appointment.getDonor().getId());
            donor.setChances(donor.getChances()-1);
            appointmentRepo.deleteById(id);
        }
    }

    @Override
    public Page<Appointment> findDoctorAppointments(Doctor doctor, Pageable pageable) {
        return appointmentRepo.findByDoctor(doctor, pageable);
    }
    @Override
    public List<Appointment> findDoctorAppointmentsToday(Doctor doctor) {
        return appointmentRepo.findDoctorAppointmentsToday(doctor);
    }
    @Override
    public int getAppointmentCountByDateAndDonationCenter(Date date, DonationCenter donationCenter) {
        return appointmentRepo.getAppointmentCountByDateAndDonationCenter(date, donationCenter);
    }
    @Override
    public List<Appointment> getAppointmentsByDateRange(Date startDate, Date endDate) {

        List<Appointment> appointments = appointmentRepo.getAppointmentsByDateRange(startDate, endDate);

        return appointments;
    }


}
