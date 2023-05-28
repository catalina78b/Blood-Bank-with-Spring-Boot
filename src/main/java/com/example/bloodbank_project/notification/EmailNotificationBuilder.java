package com.example.bloodbank_project.notification;

import com.example.bloodbank_project.entity.Appointment;

public class EmailNotificationBuilder {

    private StringBuilder bodyBuilder;

    public EmailNotificationBuilder() {
        this.bodyBuilder = new StringBuilder();
    }

    public EmailNotificationBuilder withGreeting(String greeting) {
        bodyBuilder.append(greeting).append("\n\n");
        return this;
    }

    public EmailNotificationBuilder withReminderText(String reminderText) {
        bodyBuilder.append(reminderText).append("\n\n");
        return this;
    }

    public EmailNotificationBuilder withAppointmentDetails(Appointment appointment) {

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
