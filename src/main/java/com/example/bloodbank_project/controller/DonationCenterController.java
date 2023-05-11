package com.example.bloodbank_project.controller;


import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.service.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/donation-centers")
public class DonationCenterController {

    @Autowired
    private DonationCenterService donationCenterService;

    @GetMapping
    public ResponseEntity<?> getLocations(){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters();
        return ResponseEntity.ok(donationCenters);
    }
}