package com.example.bloodbank_project.controller;


import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.service.DonationCenterService;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/donation-centers")
public class DonationCenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private DonationCenterService donationCenterService;

    @GetMapping
    public ResponseEntity<?> getLocations(){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters();
        return ResponseEntity.ok(donationCenters);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<DonationCenter>> getDonationCentersForDonor(@PathVariable int id) {
        Donor currentDonor = userService.findDonorById(id);
        String donorCounty = currentDonor.getCounty();

        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters()
                .stream()
                .filter(center -> center.getCounty().equals(donorCounty))
                .collect(Collectors.toList());

        return ResponseEntity.ok(donationCenters);
    }
}