package com.example.bloodbank_project.lottery;

import com.example.bloodbank_project.entity.Donor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Scope("singleton")
public class Lottery {
    private WinnerSelectionStrategy winnerSelectionStrategy;

    @Autowired
    public Lottery(WinnerSelectionStrategy winnerSelectionStrategy) {
        this.winnerSelectionStrategy = winnerSelectionStrategy;
    }

    public void setWinnerSelectionStrategy(WinnerSelectionStrategy winnerSelectionStrategy) {
        this.winnerSelectionStrategy = winnerSelectionStrategy;
    }

    public Donor computeWinner(List<Donor> donors) {
        return winnerSelectionStrategy.selectWinner(donors);
    }
}
