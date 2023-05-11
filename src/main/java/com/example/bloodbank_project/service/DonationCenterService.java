package com.example.bloodbank_project.service;

import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.repository.DonationCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationCenterService {

    public void saveDonationCenter(DonationCenter donationCenter);
    public List<DonationCenter> findAllDonationCenters();
    public DonationCenter findDonationCenterByName(String name);
    public DonationCenter findDonationCenterById(int id);
    public List<DonationCenter> findAllDonationCentersInCounty(String county);
}
