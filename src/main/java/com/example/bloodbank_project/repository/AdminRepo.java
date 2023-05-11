package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AdminRepo  extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email);
    Admin save(Admin admin);
}
