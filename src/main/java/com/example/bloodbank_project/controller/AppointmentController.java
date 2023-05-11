package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.AppointmentDTO;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.response.AppointmentResponse;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        return ResponseEntity.ok(new AppointmentResponse(true,false,false));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Appointment>> getDonorAppointments(@PathVariable int id){
        Donor currentDonor = userService.findDonorById(id);
        List<Appointment> appointments = appointmentService.findDonorAppointments(currentDonor);
        return ResponseEntity.ok(appointments);
    }

}
