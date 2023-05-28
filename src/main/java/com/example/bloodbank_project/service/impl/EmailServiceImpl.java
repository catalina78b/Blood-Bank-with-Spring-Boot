package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.repository.AppointmentRepo;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.EmailService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendAppointmentConfirmationEmail(AppointmentDTO appointment) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(userService.findDonorById(appointment.getDonorId()).getEmail());
        helper.setSubject("Appointment Confirmation");

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Your appointment has been created.\n");
        emailContent.append("Appointment Details:\n");
        emailContent.append("Date: ").append(appointment.getDate()).append("\n");
        emailContent.append("Donation Center: ").append(appointment.getDonationCenter().getCenterName()).append("\n");
        //emailContent.append("Doctor: ").append(userService.findDoctorByAppointmentId(appointment.getId()).getLastName()).append(userService.findDoctorByAppointmentId(appointment.getId()).getLastName()).append("\n");

        helper.setText(emailContent.toString());

        System.out.println("aici");

        emailSender.send(message);

        System.out.println("aici");
    }
    @Override
    public void sendLotteryWinnerEmail(Donor winner) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(winner.getEmail());
        helper.setSubject("Congratulations! You are the lottery winner");

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Dear ").append(winner.getFirstName()).append(" ").append(winner.getLastName()).append("!")
                .append("Thank you for helping our community every day! As a reward for this, you won a package of free tests at your favorite clinic!\n")
                .append("We are waiting for a confirmation email and please let us know the chosen center and a convenient date for your free appointment!\n");

        helper.setText(emailContent.toString());

        emailSender.send(message);
    }


}
