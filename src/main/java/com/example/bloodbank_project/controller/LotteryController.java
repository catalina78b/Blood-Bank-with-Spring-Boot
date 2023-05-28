package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.entity.Donor;
import com.example.bloodbank_project.service.EmailService;
import com.example.bloodbank_project.service.LotteryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/lottery")
public class LotteryController {

    private final LotteryService lotteryService;

    private final EmailService emailService;

    public LotteryController(LotteryService lotteryService, EmailService emailService) {
        this.lotteryService = lotteryService;
        this.emailService = emailService;
    }

    @GetMapping("/compute-winner")
    public ResponseEntity<Donor> computeWinner(){
        Donor winner = lotteryService.computeWinner();
        try{
        emailService.sendLotteryWinnerEmail(winner);
        System.out.println("Email sent to winner!");}
        catch(MessagingException e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.ok(winner);
    }
}

