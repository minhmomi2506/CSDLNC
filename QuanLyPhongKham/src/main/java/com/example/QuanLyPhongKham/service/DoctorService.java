package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.QuanLyPhongKham.entity.Doctor;

@Service
public interface DoctorService {
	List<Doctor> getAll();
	
	Doctor addDoctor(Doctor doctor);
	
	Doctor editDoctor(Long id, Doctor doctor);
	
	void deleteById(Long id);
	
	Doctor findDoctorById(@PathVariable Long id);
}
