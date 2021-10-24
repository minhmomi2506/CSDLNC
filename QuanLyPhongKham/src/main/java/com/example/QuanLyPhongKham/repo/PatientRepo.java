package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
	Patient findPatientById(Long id);
}
