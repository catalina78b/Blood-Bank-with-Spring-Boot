package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.entity.User;
import com.example.bloodbank_project.repository.AppointmentRepo;
import com.example.bloodbank_project.repository.DonationCenterRepo;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public void saveAppointment(AppointmentDTO appointmentDTO) throws ParseException {
        Appointment appointment = new Appointment();
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        Date date = formatter.parse(formatter.format(appointmentDTO.getDate()));
        appointment.setDate(date);
        appointment.setDonor(userService.findDonorById(appointmentDTO.getDonorId()));
        appointment.setDonationCenter(donationCenterRepo.findDonationCenterById(appointmentDTO.getDonationCenter().getId()));
        appointment.setStatus(false);

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
            appointmentRepo.deleteById(id);
        }
    }
}
