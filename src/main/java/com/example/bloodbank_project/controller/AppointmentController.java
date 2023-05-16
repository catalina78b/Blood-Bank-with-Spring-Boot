package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.response.AppointmentResponse;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @PostMapping("/add-appointment")
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ParseException {
        appointmentService.saveAppointment(appointmentDTO);
        return ResponseEntity.ok(new AppointmentResponse(true, false, false));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Appointment>> getDonorAppointments(@PathVariable int id) {
        Donor currentDonor = userService.findDonorById(id);
        List<Appointment> appointments = appointmentService.findDonorAppointments(currentDonor);
        return ResponseEntity.ok(appointments);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable int id) {
        boolean valid=false;
        Appointment appointment = appointmentService.findById(id);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the appointment date
        Date appointmentDate = appointment.getDate();
        Instant instant = appointmentDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate appointmentLocalDate = instant.atZone(zoneId).toLocalDate();


        // Calculate the difference in days
        long daysDifference = ChronoUnit.DAYS.between(currentDate, appointmentLocalDate);

        if (daysDifference >= 0 && daysDifference <= 1 && !appointment.isStatus()) {
            // Delete the appointment
            valid=true;
            appointmentService.deleteAppointmentById(id);
            return ResponseEntity.ok(valid);
        }

        return ResponseEntity.ok(valid);
    }


}
