package com.example.bloodbank_project.lottery;

import com.example.bloodbank_project.entity.Donor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public
class RandomWinnerSelectionStrategy implements WinnerSelectionStrategy {
    @Override
    public Donor selectWinner(List<Donor> donors) {
        if (donors.isEmpty()) {
            return null;
        }

        int totalChances = calculateTotalChances(donors);

        Random random = new Random();
        int winningNumber = random.nextInt(totalChances);

        int cumulativeChances = 0;
        for (Donor donor : donors) {
            cumulativeChances += donor.getChances();
            if (winningNumber < cumulativeChances) {
                return donor;
            }
        }

        return null;
    }

    private int calculateTotalChances(List<Donor> donors) {
        int totalChances = 0;
        for (Donor donor : donors) {
            totalChances += donor.getChances();
        }
        return totalChances;
    }
}