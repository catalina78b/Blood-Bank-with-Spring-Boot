package com.example.bloodbank_project.service;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.dto.DonorDTO;
import com.example.bloodbank_project.dto.LoginDTO;
import com.example.bloodbank_project.entity.*;
import com.example.bloodbank_project.response.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
     User findUserByEmail(String email);
     List<Doctor> findAllDoctors();
     Doctor saveDoctor(DoctorDTO doctorDTO);
     void saveDonor(DonorDTO donorDTO);
     void saveAdmin(Admin admin);
     Admin findAdminByEmail(String email);
     void deleteDoctorById(int doctorId);
     Doctor findDoctorById(int doctorId);
     public void updateDoctor(int id,String firstName, String lastName, String email, String password,String CNP, DonationCenter donationCenter );
     Donor findDonorById(int donorId);
     void deleteDonorById(int donorId);
     void updateDonor(int id,String firstName, String lastName, String email, String password,String county, String bloodType,String phoneNumber );
     LoginResponse loginUser(LoginDTO loginDTO);
     public Doctor findDoctorWithMinAppointments(DonationCenter donationCenter);
}
