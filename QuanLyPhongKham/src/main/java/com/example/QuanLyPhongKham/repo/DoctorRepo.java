package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	Doctor findDoctorById(Long id);
}	
