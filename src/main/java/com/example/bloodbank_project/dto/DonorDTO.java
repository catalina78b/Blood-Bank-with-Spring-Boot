package com.example.bloodbank_project.dto;

import com.example.bloodbank_project.entity.Role;

public class DonorDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String bloodType;
    private String county;
    private String phoneNumber;


    public DonorDTO(int id, String firstName, String lastName, String email, String password, Role role, String county, String bloodType,String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.county=county;
        this.bloodType=bloodType;
        this.phoneNumber=phoneNumber;
    }

    public DonorDTO(int id, String firstName, String lastName, String email, String password, String county, String bloodType,String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.county=county;
        this.bloodType=bloodType;
        this.phoneNumber=phoneNumber;
    }


    public DonorDTO() {
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
}
