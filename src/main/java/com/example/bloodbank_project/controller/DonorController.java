package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.dto.DonorDTO;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("/api/donors")
public class DonorController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable  int id){
        Donor donor = userService.findDonorById(id);
        return ResponseEntity.ok(donor);
    }
    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable int id,@RequestBody DonorDTO donorDTO) {
        Donor donor = userService.findDonorById(id);
        if(donor!=null)
            userService.updateDonor(id, donorDTO.getFirstName(),
                    donorDTO.getLastName(),donorDTO.getEmail(),donorDTO.getPassword(),
                    donorDTO.getCounty(),donorDTO.getBloodType(),donorDTO.getPhoneNumber());
        return ResponseEntity.ok(userService.findDonorById(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDonor(@PathVariable int id){

        Donor donor = userService.findDonorById(id);
        if(donor==null)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        userService.deleteDonorById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
