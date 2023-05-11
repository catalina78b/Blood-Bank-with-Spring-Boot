package com.example.bloodbank_project.config;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.entity.Admin;
import com.example.bloodbank_project.entity.Doctor;
import com.example.bloodbank_project.entity.DonationCenter;
import com.example.bloodbank_project.entity.Role;
import com.example.bloodbank_project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorConfig {
    private UserService userService;

    public DoctorConfig(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public CommandLineRunner initializeDatabase2()
    {
        return args->{
            DoctorDTO doctor=new DoctorDTO(0,"Emma","Evelyn","eve@yahoo.com","user123",new Role("DOCTOR"),new DonationCenter(1,"Regina Maria","Cluj","Str.Republicii nr.16",true,"L-V open",10),"97391283979");
            if(userService.findUserByEmail(doctor.getEmail())==null)
            {userService.saveDoctor(doctor);}
        };
    }

}