package com.example.bloodbank_project.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role{
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;


    public int getId() {
        return id;
    }
    public Role(String name){
        this.name = name;
    }

    public Role(){}

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

