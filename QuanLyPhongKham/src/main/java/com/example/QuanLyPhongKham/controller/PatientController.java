package com.example.QuanLyPhongKham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.Patient;
import com.example.QuanLyPhongKham.service.PatientService;

@RestController
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/listPatients")
	public List<Patient> listDoctors() {
		return patientService.getAll();
	}
	
	@PostMapping("/addPatient")
	public ResponseEntity<Patient> addDoctor(@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(patientService.addPatient(patient), HttpStatus.OK);
	} 
	
	@PutMapping("/editPatient/{id}")
	public ResponseEntity<Patient> editExamination(@RequestBody Patient patient, @PathVariable Long id) {
		return new ResponseEntity<Patient>(patientService.editPatient(id, patient), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePatient/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.deleteById(id);
	}
}
