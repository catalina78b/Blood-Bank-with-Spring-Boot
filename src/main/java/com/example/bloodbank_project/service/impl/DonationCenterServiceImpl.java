package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.repository.DonationCenterRepo;
import com.example.bloodbank_project.service.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationCenterServiceImpl implements DonationCenterService {

    private DonationCenterRepo donationCenterRepository;

    public DonationCenterServiceImpl(DonationCenterRepo donationCenterRepository) {
        this.donationCenterRepository = donationCenterRepository;
    }

    public void saveDonationCenter(DonationCenter donationCenter){
        donationCenterRepository.save(donationCenter);
    }

    public List<DonationCenter> findAllDonationCenters(){
        List<DonationCenter> donationCenters = donationCenterRepository.findAll();
        return donationCenters;
    }

    public DonationCenter findDonationCenterByName(String name){
        return donationCenterRepository.findDonationCenterByName(name);
    }

    @Override
    public DonationCenter findDonationCenterById(int id) {
        return  donationCenterRepository.findDonationCenterById(id);
    }

    public List<DonationCenter> findAllDonationCentersInCounty(String county){
        return donationCenterRepository.findAllDonationCentersInCounty(county);
    }
}
