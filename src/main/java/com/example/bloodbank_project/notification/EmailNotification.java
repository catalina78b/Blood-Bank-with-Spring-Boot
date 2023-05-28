package com.example.bloodbank_project.notification;

import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Donor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailNotification implements Notification{

    private Appointment appointment;

    public EmailNotification(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void notifyUser() {
        Donor donor = appointment.getDonor();
        DonationCenter donationCenter = appointment.getDonationCenter();

        String body = new EmailNotificationBuilder()
                .withGreeting("Hello, " + donor.getFirstName())
                .withReminderText("This is a reminder that you have an appointment tomorrow at " + donationCenter.getCenterName())
                .withAppointmentDetails(appointment)
                .build();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(donor.getEmail());
        message.setSubject("Appointment reminder");
        message.setText(body);
        getJavaMailSender().send(message);
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("bloodbankofficial9@gmail.com");
        mailSender.setPassword("hfmagizzjkkcqgpo");

        // Additional properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}
