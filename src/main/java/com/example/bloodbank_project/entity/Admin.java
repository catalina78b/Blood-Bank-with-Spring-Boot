package com.example.bloodbank_project.entity;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Admin() {
    }
}
