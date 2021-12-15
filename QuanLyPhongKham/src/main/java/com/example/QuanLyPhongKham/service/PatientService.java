package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Patient;

@Service
public interface PatientService {
	List<Patient> getAll();
	
	Patient addPatient(Patient patient);
	
	Patient editPatient(Long id, Patient patient);
	
	void deleteById(Long id);

}
