package com.example.bloodbank_project.config;

import com.example.bloodbank_project.entity.Admin;
import com.example.bloodbank_project.entity.Role;
import com.example.bloodbank_project.entity.User;
import com.example.bloodbank_project.repository.AdminRepo;
import com.example.bloodbank_project.repository.UserRepo;
import com.example.bloodbank_project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {
    private UserService userService;

    public AdminConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public CommandLineRunner initializeDatabase1()
    {
        return args->{
            Admin admin=new Admin("Cătălina","Buruian","admin@yahoo.com","admin123");
            if(userService.findAdminByEmail(admin.getEmail())==null)
            {userService.saveAdmin(admin);}
        };
    }

}
