package com.example.bloodbank_project.service.impl;

import com.example.bloodbank_project.dto.DoctorDTO;
import com.example.bloodbank_project.dto.DonorDTO;
import com.example.bloodbank_project.dto.LoginDTO;
import com.example.bloodbank_project.entity.*;
import com.example.bloodbank_project.repository.*;
import com.example.bloodbank_project.response.LoginResponse;
import com.example.bloodbank_project.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepository;
    private DonorRepo donorRepo;
    private DoctorRepo doctorRepo;
    private AdminRepo adminRepo;
    private PasswordEncoder passwordEncoder;
    private RoleRepo roleRepo;
    private AppointmentRepo appointmentRepo;

    public UserServiceImpl(UserRepo userRepository,AdminRepo adminRepo,DonorRepo donorRepo,RoleRepo roleRepo,DoctorRepo doctorRepo,AppointmentRepo appointmentRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.donorRepo=donorRepo;
        this.doctorRepo=doctorRepo;
        this.roleRepo=roleRepo;
        this.adminRepo=adminRepo;
        this.passwordEncoder = passwordEncoder;
        this.appointmentRepo=appointmentRepo;

    }

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor saveDoctor(DoctorDTO doctorDTO) {
        if(roleRepo.findRole("DOCTOR")==null){
            Role role = new Role("DOCTOR");
            roleRepo.save(role);
            doctorDTO.setRole(role);
        }
        else{
            doctorDTO.setRole(roleRepo.findRole("DOCTOR"));
        }
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setRole(doctorDTO.getRole());
        doctor.setCNP(doctorDTO.getCnp());
        doctor.setDonationCenter(doctorDTO.getDonationCenter());
        doctorRepo.save(doctor);

        return doctor;
    }

    @Override
    public void saveAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        if(roleRepo.findRole("ADMIN")==null){
            Role role = new Role("ADMIN");
            roleRepo.save(role);
            admin.setRole(role);
        }
        else{
            admin.setRole(roleRepo.findRole("ADMIN"));
        }
        adminRepo.save(admin);
    }

    @Override
    public void saveDonor(DonorDTO donorDTO) {
        if(roleRepo.findRole("DONOR")==null){
            Role role = new Role("DONOR");
            roleRepo.save(role);
            donorDTO.setRole(role);
        }
        else{
            donorDTO.setRole(roleRepo.findRole("DONOR"));
        }
        Donor donor = new Donor();
        donor.setFirstName(donorDTO.getFirstName());
        donor.setLastName(donorDTO.getLastName());
        donor.setEmail(donorDTO.getEmail());
        donor.setPassword(donorDTO.getPassword());
        donor.setBloodType(donorDTO.getBloodType());
        donor.setCounty(donorDTO.getCounty());
        donor.setRole(donorDTO.getRole());
        donorRepo.save(donor);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    @Override
    public void deleteDoctorById(int doctorId) {
        doctorRepo.deleteById(doctorId);
    }
    @Override
    public Doctor findDoctorById(int doctorId){
        return doctorRepo.findDoctorById(doctorId);
    }

    @Override
    public Doctor findDoctorWithMinAppointments(DonationCenter donationCenter){
        List<Doctor> doctorList = findAllDoctors();
        if(doctorList.isEmpty()){
            return null;
        }
        if(doctorList.size() == 1){
            return doctorList.get(0);
        }
        Map.Entry<Doctor,Integer> min = null;
        Map<Doctor, Integer> doctorsAndNumberOfAppointments = new HashMap<>();
        for (Doctor doctor : doctorList) {
            if (doctor.getDonationCenter().equals(donationCenter)) {
                List<Appointment> appointments = appointmentRepo.getAppointmentsByDoctor(doctor);
                doctorsAndNumberOfAppointments.put(doctor, appointments.size());
            }
        }
        for (Map.Entry<Doctor, Integer> entry : doctorsAndNumberOfAppointments.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }
    @Override
    public void updateDoctor(int id,String firstName, String lastName, String email,String password, String CNP, DonationCenter donationCenter){
        doctorRepo.updateDoctor(id,firstName,lastName,email,password,CNP, donationCenter);
    }
    @Override
    public Donor findDonorById(int donorId){
        return donorRepo.findDonorById(donorId);
    }
    @Override
    public void deleteDonorById(int donorId){
        donorRepo.deleteById(donorId);
    }
    @Override
    public void updateDonor(int id,String firstName, String lastName, String email,String password, String county,String bloodType,String phoneNumber){
        donorRepo.updateDonor(id,firstName,lastName,
                email,password,county,bloodType,phoneNumber);
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user=userRepository.findByEmail(loginDTO.getEmail());
        if(user!=null)
        {
            if(Objects.equals(user.getPassword(), loginDTO.getPassword()))
            {
                return new LoginResponse("Login Success",true);
            }
            else
                return new LoginResponse("Password not match",false);
        }
        else
            return new LoginResponse("Email don't exist",false);
    }


}
