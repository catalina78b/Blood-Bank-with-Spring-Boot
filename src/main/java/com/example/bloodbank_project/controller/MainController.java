package com.example.bloodbank_project.controller;

import com.example.bloodbank_project.dto.DonorDTO;
import com.example.bloodbank_project.dto.LoginDTO;
import com.example.bloodbank_project.entity.User;
import com.example.bloodbank_project.response.LoginResponse;
import com.example.bloodbank_project.security.JwtUtil;
import com.example.bloodbank_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        LoginResponse loginResponse=userService.loginUser(loginDTO);
        if(loginResponse.isStatus()) {
            User user=userService.findUserByEmail(loginDTO.getEmail());
            String token = JwtUtil.generateToken(user.getEmail(), user.getRoleName());
            String role = user.getRoleName();
            int id = user.getId();
            System.out.println(id);
            return ResponseEntity.ok(new LoginResponse(token, role,id));
        }
        else
        {
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody DonorDTO donorDTO) {
        User existingUser = userService.findUserByEmail(donorDTO.getEmail());
        boolean accountCreated;
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            accountCreated=false;
        } else {
            userService.saveDonor(donorDTO);
            accountCreated=true;
        }
        return ResponseEntity.ok(accountCreated);
    }
}
