package com.example.bloodbank_project.entity;

import javax.persistence.*;

@Entity
public class DonationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, updatable = false)
    private String centerName;

    @Column(nullable = false, updatable = false)
    private String county;

    @Column(nullable = false, updatable = false)
    private String addressDetails;

    @Column(nullable = false, updatable = false)
    private boolean functional;

    @Column(nullable = false, updatable = false)
    private String schedule;

    @Column(nullable = false, updatable = false)
    private Integer maxDonations;

    public DonationCenter(int id, String centerName, String location, String addressDetails, boolean functional, String schedule, Integer maxDonations) {
        this.id = id;
        this.centerName = centerName;
        this.county = location;
        this.addressDetails = addressDetails;
        this.functional = functional;
        this.schedule = schedule;
        this.maxDonations = maxDonations;
    }

    public DonationCenter() {

    }
    public void setId() {
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String location) {
        this.county = location;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public boolean isFunctional() {
        return functional;
    }

    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getMaxDonations() {
        return maxDonations;
    }

    public void setMaxDonations(Integer maxDonations) {
        this.maxDonations = maxDonations;
    }
}
