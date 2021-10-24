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

import com.example.QuanLyPhongKham.entity.Doctor;
import com.example.QuanLyPhongKham.service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/listDoctors")
	public List<Doctor> listDoctors() {
		return doctorService.getAll();
	}
	
	@PostMapping("/addDoctor")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		return new ResponseEntity<Doctor>(doctorService.addDoctor(doctor), HttpStatus.OK);
	} 
	
	@PutMapping("/editDoctor/{id}")
	public ResponseEntity<Doctor> editExamination(@RequestBody Doctor doctor, @PathVariable Long id) {
		return new ResponseEntity<Doctor>(doctorService.editDoctor(id, doctor), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteDoctor/{id}")
	public void deleteDoctor(@PathVariable Long id) {
		doctorService.deleteById(id);
	}
	
	@GetMapping("/findDoctor/{id}")
	public ResponseEntity<Doctor> findDoctor(@PathVariable Long id) {
		return new ResponseEntity<Doctor>(doctorService.findDoctorById(id), HttpStatus.OK);
	} 
}
