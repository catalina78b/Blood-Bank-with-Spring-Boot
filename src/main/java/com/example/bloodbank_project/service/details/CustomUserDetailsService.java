package com.example.bloodbank_project.service.details;

import com.example.bloodbank_project.entity.User;
import com.example.bloodbank_project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("No user found for the given email");
        }
        return new CustomUserDetails(user);
    }
}
