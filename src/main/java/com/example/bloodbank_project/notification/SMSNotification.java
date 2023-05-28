package com.example.bloodbank_project.notification;

import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Donor;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;


public class SMSNotification implements Notification{

    private Appointment appointment;

    public SMSNotification(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void notifyUser() {
        Donor donor = appointment.getDonor();
        DonationCenter donationCenter = appointment.getDonationCenter();

        // Construct the body using the Builder pattern
        String body = new SMSNotificationBuilder()
                .withGreeting("Hello, " + donor.getFirstName())
                .withReminderText("This is a reminder that you have an appointment tomorrow at " + donationCenter.getCenterName())
                .withAppointmentDetails(appointment)
                .build();

        // Send the SMS notification
        MessageCreator creator = Message.creator(
                new PhoneNumber("+40" + donor.getPhoneNumber()),
                new PhoneNumber("+12545365834"),
                body
        );
        creator.create();
    }

}
