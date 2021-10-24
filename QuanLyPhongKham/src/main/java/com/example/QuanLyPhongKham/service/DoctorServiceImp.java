package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Doctor;
import com.example.QuanLyPhongKham.repo.DoctorRepo;

@Component
public class DoctorServiceImp implements DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;

	@Override
	public List<Doctor> getAll() {
		// TODO Auto-generated method stub
		return doctorRepo.findAll();
	}
	
	@Override
	public Doctor addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorRepo.save(doctor);
	}
	
	@Override
	public Doctor editDoctor(Long id, Doctor doctor) {
		Doctor doctor1 = doctorRepo.findDoctorById(id);
		doctor1.setDoctorName(doctor.getDoctorName());
		doctor1.setGender(doctor.getGender());
		doctor1.setDateOfBirth(doctor.getDateOfBirth());
		doctor1.setPhoneNumber(doctor.getPhoneNumber());
		doctor1.setIdentityCardNumber(doctor.getIdentityCardNumber());
		doctor1.setAddress(doctor.getAddress());
		doctor1.setSalary(doctor.getSalary());
		doctor1.setDepartment(doctor.getDepartment());
		return doctorRepo.save(doctor1);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		doctorRepo.deleteById(id);
	}
	
	@Override
	public Doctor findDoctorById(Long id) {
		// TODO Auto-generated method stub
		return doctorRepo.findDoctorById(id);
	}

}
