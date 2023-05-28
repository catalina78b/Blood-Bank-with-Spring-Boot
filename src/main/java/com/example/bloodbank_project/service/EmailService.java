package com.example.bloodbank_project.service;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Donor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailService {
    void sendAppointmentConfirmationEmail(AppointmentDTO appointmentDTO) throws MessagingException;
    void sendLotteryWinnerEmail(Donor winner) throws MessagingException;
}
