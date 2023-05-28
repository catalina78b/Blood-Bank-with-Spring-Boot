package com.example.bloodbank_project.dto;

import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;

import java.util.Date;

public class AppointmentDTO {

    private int id;
    private boolean status;
    private int donorId;
    private DonationCenter donationCenter;
    private Date date;
    private Doctor doctor;
    private String notification;

    public AppointmentDTO(int id, boolean status, int donorId, DonationCenter donationCenter, Date date, Doctor doctor, String notification) {
        this.id = id;
        this.status = status;
        this.donorId = donorId;
        this.donationCenter = donationCenter;
        this.date = date;
        this.doctor = doctor;
        this.notification = notification;
    }

    public AppointmentDTO(int id, boolean status, int donorId, DonationCenter donationCenter, Date date, Doctor doctor) {
        this.id = id;
        this.status = status;
        this.donorId = donorId;
        this.donationCenter = donationCenter;
        this.date = date;
        this.doctor = doctor;
    }

    public AppointmentDTO(int donorId, DonationCenter donationCenter, Date date,String notification) {
        this.donorId = donorId;
        this.donationCenter = donationCenter;
        this.date = date;
        this.notification=notification;
    }

    public AppointmentDTO() {
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
