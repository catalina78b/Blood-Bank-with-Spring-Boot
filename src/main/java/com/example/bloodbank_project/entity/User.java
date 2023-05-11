package com.example.bloodbank_project.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @Column(name="id",length=45)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Column(name="firstname",length=255,nullable = false)
    private String firstName;

    @Column(name="lastname",length=255,nullable = false)
    private String lastName;

    @Column(name="email",length=255,nullable = false)
    private String email;

    @Column(name="password",length=255,nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User( String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
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

    public String getRoleName(){
        return this.role.getName();
    }

}
