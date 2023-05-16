package com.example.bloodbank_project.service;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.Donor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface AppointmentService {

    List<Appointment> findDonorAppointments(Donor donor);
    public void saveAppointment(AppointmentDTO appointmentDTO) throws ParseException;
    public List<Appointment> findAllDoctorAppointments(Doctor doctor);
    Appointment findById(int id);
    public void updateAppointment(int id,boolean status);
    public void deleteAppointmentById(int id);

}
