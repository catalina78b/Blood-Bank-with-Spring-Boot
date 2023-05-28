package com.example.bloodbank_project.scheduler;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.notification.Notification;
import com.example.bloodbank_project.notification.NotificationFactory;
import com.example.bloodbank_project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class AppointmentReminderScheduler {

    @Autowired
    private AppointmentService appointmentService;

    @Scheduled(cron = "0 15 17 * * ?") // Runs every day at 15:17 PM
    public void sendAppointmentReminders() {
        LocalDate currentDate = LocalDate.now();
        LocalDate reminderDate = currentDate.plusDays(1);
        LocalDateTime reminderDateTimeStart = reminderDate.atStartOfDay();
        LocalDateTime reminderDateTimeEnd = reminderDate.atTime(LocalTime.MAX);

        Date reminderDateStart = convertToDate(reminderDateTimeStart);
        Date reminderDateEnd = convertToDate(reminderDateTimeEnd);
        Notification notification=null;

        List<Appointment> appointments = appointmentService.getAppointmentsByDateRange(reminderDateStart, reminderDateEnd);
        for (Appointment appointment : appointments) {
            if(appointment.getNotification()!=null)
             notification = NotificationFactory.createNotification(appointment);
            if (notification != null) {
                notification.notifyUser();
            }
        }
    }
    private Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

