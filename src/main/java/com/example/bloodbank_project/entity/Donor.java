package com.example.bloodbank_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Donor extends User{
    @Column
    private String bloodType;
    @Column
    private String county;
    @Column
    private String phoneNumber;
    @Column(columnDefinition = "integer default 0")
    private Integer chances;



    public Donor(String firstName, String lastName, String email, String password, String bloodType, String county,String phoneNumber,Integer chances) {
        super(firstName, lastName, email, password);
        this.bloodType = bloodType;
        this.county = county;
        this.phoneNumber=phoneNumber;
        this.chances=chances;
    }

    public Donor(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Donor() {

    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getChances() {
        return chances;
    }

    public void setChances(Integer chances) {
        this.chances = chances;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "bloodType='" + bloodType + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
