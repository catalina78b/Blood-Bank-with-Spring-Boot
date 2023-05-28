package com.example.bloodbank_project.notification;

import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class SMSNotificationBuilder {

    @Autowired
    private UserService userService;

    private StringBuilder bodyBuilder;

    public SMSNotificationBuilder() {
        this.bodyBuilder = new StringBuilder();
    }

    public SMSNotificationBuilder withGreeting(String greeting) {
        bodyBuilder.append(greeting).append("\n\n");
        return this;
    }

    public SMSNotificationBuilder withReminderText(String reminderText) {
        bodyBuilder.append(reminderText).append("\n\n");
        return this;
    }

    public SMSNotificationBuilder withAppointmentDetails(Appointment appointment) {

        bodyBuilder.append("Appointment Details:")
                .append("\nDate: ").append(appointment.getDate())
                .append("\nDonation Center: ").append(appointment.getDonationCenter().getCenterName())
                //.append("\nDoctor: ").append(appointment.getDoctor().getFirstName())
                .append(" ")
                //.append(appointment.getDoctor().getLastName())
                .append("\n\n");
        return this;
    }

    public String build() {
        return bodyBuilder.toString();
    }
}