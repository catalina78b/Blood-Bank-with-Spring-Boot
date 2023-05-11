package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.service.DonationCenterService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/doctors")
public class DoctorController {
    @Autowired
    UserService userService;

    @Autowired
    DonationCenterService donationCenterService;

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
        userService.deleteDoctorById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
