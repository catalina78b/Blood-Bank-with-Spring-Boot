package com.example.bloodbank_project.lottery;

import com.example.bloodbank_project.entity.Donor;

import java.util.List;

public interface WinnerSelectionStrategy {
        Donor selectWinner(List<Donor> donors);
}
