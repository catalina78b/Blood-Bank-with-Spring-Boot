package com.example.bloodbank_project.dto;

import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Role;

public class DoctorDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private DonationCenter donationCenter;
    private String cnp;
    private int donationCenterId;


    public DoctorDTO(int id, String firstName, String lastName, String email, String password, Role role, DonationCenter donationCenter, String cnp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.donationCenter = donationCenter;
        this.cnp = cnp;
    }

    public DoctorDTO(int id, String firstName, String lastName, String email, String password, Role role, DonationCenter donationCenter, String cnp, int donationCenterId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.donationCenter = donationCenter;
        this.cnp = cnp;
        this.donationCenterId = donationCenterId;
    }

    public DoctorDTO() {
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public int getDonationCenterId() {
        return donationCenterId;
    }

    public void setDonationCenterId(int donationCenterId) {
        this.donationCenterId = donationCenterId;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    }
