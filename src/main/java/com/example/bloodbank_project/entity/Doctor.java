package com.example.bloodbank_project.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Doctor extends User{
    @OneToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;

    private String CNP;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String email, String password,String CNP,DonationCenter donationCenter) {
        super(firstName, lastName, email, password);
        this.CNP=CNP;
        this.donationCenter=donationCenter;
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "donationCenter=" + donationCenter.getCenterName() +
                ", CNP='" + CNP + '\'' +
                '}';
    }
}
