package com.example.bloodbank_project.service;

import com.example.bloodbank_project.entity.Donor;
import org.springframework.stereotype.Service;

@Service
public interface LotteryService {
    public Donor computeWinner();
}
