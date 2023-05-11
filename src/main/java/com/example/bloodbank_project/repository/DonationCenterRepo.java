package com.example.bloodbank_project.repository;

import com.example.bloodbank_project.entity.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface DonationCenterRepo extends JpaRepository<DonationCenter, Integer> {
    @Query("SELECT d FROM DonationCenter d WHERE d.centerName = :name")
    DonationCenter findDonationCenterByName(@Param("name") String name);

    @Query("SELECT d FROM DonationCenter d WHERE d.id = :id")
    DonationCenter findDonationCenterById(@Param("id") int id);

    @Query("SELECT d FROM DonationCenter d WHERE d.county = :county")
    List<DonationCenter> findAllDonationCentersInCounty(@Param("county") String county);
}
