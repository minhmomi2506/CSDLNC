package com.example.QuanLyPhongKham.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Patient;
import com.example.QuanLyPhongKham.repo.PatientRepo;

@Component
@Transactional
public class PatientServiceImp implements PatientService {
	
	@Autowired
	private PatientRepo patientRepo;

	@Override
	public List<Patient> getAll() {
		// TODO Auto-generated method stub
		return patientRepo.findAllPatients();
	}
	
	@Override
	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepo.save(patient);
	}
	
	@Override
	public Patient editPatient(Long id, Patient patient) {
		Patient patient1 = patientRepo.findPatientById(id);
		patient1.setFullName(patient.getFullName());
		patient1.setGender(patient.getGender());
		patient1.setDateOfBirth(patient.getDateOfBirth());
		patient1.setPhoneNumber(patient.getPhoneNumber());
		patient1.setAddress(patient.getAddress());
		return patientRepo.save(patient1);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		patientRepo.deletePatient(id);
	}
}
