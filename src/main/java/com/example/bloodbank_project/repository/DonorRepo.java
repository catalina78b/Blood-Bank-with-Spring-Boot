package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface DonorRepo  extends JpaRepository<Donor, Integer> {

    Donor findByEmail(String email);
    Donor findDonorById(@Param("id") Integer id);
    @Query("SELECT u FROM Donor u WHERE u.email = :email")
    Donor findDonorByEmail(@Param("email") String email);
    @Modifying
    @Query("UPDATE Donor d " +
            "set d.email = :email, d.county = :county, d.lastName = :lastname, d.firstName = :firstname, d.password = :password, d.bloodType = :bloodtype,d.phoneNumber = :phonenumber" +
            "  where d.id = :donorId")
    void updateDonor(@Param("donorId") Integer donorId,@Param("firstname")String firstName, @Param("lastname")String lastname,
                     @Param("email") String email,@Param("password") String password, @Param("county") String county, @Param("bloodtype") String bloodType,@Param("phonenumber") String phoneNumber);
}
