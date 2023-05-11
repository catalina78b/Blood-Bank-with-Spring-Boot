package com.example.bloodbank_project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dashboard {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
    }

