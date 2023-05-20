package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.entity.Appointment;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.response.AppointmentResponse;
import com.example.bloodbank_project.service.AppointmentService;
import com.example.bloodbank_project.service.DonationCenterService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/doctors")
public class DoctorController {
    @Autowired
    UserService userService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return userService.findAllDoctors();
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDTO doctorDTO) {

        DonationCenter donationCenter=donationCenterService.findDonationCenterById(doctorDTO.getDonationCenterId());
        doctorDTO.setDonationCenter(donationCenter);
        Doctor doctor=userService.saveDoctor(doctorDTO);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable  int id){
        Doctor doctor = userService.findDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable int id,@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = userService.findDoctorById(id);
       if(doctor!=null)
       {
           DonationCenter donationCenter=donationCenterService.findDonationCenterById(doctorDTO.getDonationCenterId());
           userService.updateDoctor(id, doctorDTO.getFirstName(),
                doctorDTO.getLastName(),doctorDTO.getEmail(),doctorDTO.getPassword(),
                doctorDTO.getCnp(),donationCenter);}
        return ResponseEntity.ok(userService.findDoctorById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable int id){

        Doctor doctor = userService.findDoctorById(id);
        if(doctor==null)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<Appointment> appointments=appointmentService.findAllDoctorAppointments(doctor);
        for (Appointment appointment:appointments) {
            appointmentService.deleteAppointmentById(appointment.getId());
        }
        userService.deleteDoctorById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @GetMapping("/{id}/appointments")
    public ResponseEntity<Page<Appointment>> getAppointments(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Doctor doctor = userService.findDoctorById(id);
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Appointment> appointments = appointmentService.findDoctorAppointments(doctor, pageRequest);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/{id}/appointments/today")
    public ResponseEntity<List<Appointment>> getTodayAppointments(@PathVariable int id) {
        Doctor doctor = userService.findDoctorById(id);
        List<Appointment> appointments = appointmentService.findDoctorAppointmentsToday(doctor);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{doctorId}/appointments/{appointmentId}/confirm")
    @CrossOrigin
    public ResponseEntity<?> confirmAppointment(@PathVariable int doctorId, @PathVariable int appointmentId) {
        List<Appointment> appointments=appointmentService.findAllDoctorAppointments(userService.findDoctorById(doctorId));
        AppointmentResponse appointmentResponse = new AppointmentResponse(true, false, false);
        for (Appointment appointment : appointments) {
                 if(appointment.getId()==appointmentId && !appointment.isStatus())
                     appointment.setStatus(true);
                     appointmentResponse.setConfirmed(true);
                     appointmentService.updateAppointment(appointmentId,true);
             }
        return ResponseEntity.ok(appointmentResponse);
    }
}
