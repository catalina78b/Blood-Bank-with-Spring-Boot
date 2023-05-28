package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.lottery.Lottery;
import com.example.bloodbank_project.lottery.RandomWinnerSelectionStrategy;
import com.example.bloodbank_project.repository.DonorRepo;
import com.example.bloodbank_project.service.LotteryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryServiceImpl implements LotteryService {
    private final Lottery lottery;
    private final DonorRepo donorRepository;

    public LotteryServiceImpl(DonorRepo donorRepository, Lottery lottery, RandomWinnerSelectionStrategy randomWinnerSelectionStrategy) {
        this.donorRepository = donorRepository;
        this.lottery = lottery;
        this.lottery.setWinnerSelectionStrategy(randomWinnerSelectionStrategy);
    }

    @Override
    public Donor computeWinner() {
        List<Donor> donors = donorRepository.findAll();
        return lottery.computeWinner(donors);
    }
}

