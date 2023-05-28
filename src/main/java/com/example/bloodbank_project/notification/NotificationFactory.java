package com.example.bloodbank_project.notification;

import com.example.bloodbank_project.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    public static Notification createNotification(Appointment appointment) {
        if (appointment.getNotification()!=null && appointment.getNotification().equalsIgnoreCase("sms")) {
            return new SMSNotification(appointment);
        } else if (appointment.getNotification()!=null && appointment.getNotification().equalsIgnoreCase("email")) {
            return new EmailNotification(appointment);
        }
        throw new IllegalArgumentException("Invalid notification type: " + appointment.getNotification());
    }
}
