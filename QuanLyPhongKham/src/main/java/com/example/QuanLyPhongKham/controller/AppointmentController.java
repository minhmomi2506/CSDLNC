package com.example.QuanLyPhongKham.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.Appointment;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.UserRepo;
import com.example.QuanLyPhongKham.service.AppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/addAppointment")
	public ResponseEntity<Appointment> addDoctor(@RequestBody Appointment appointment, Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUsername(username);
		return new ResponseEntity<Appointment>(appointmentService.addAppointment(appointment,user), HttpStatus.OK);
	} 
	
	@GetMapping("/listAppointments")
	public List<Appointment> listDoctors(Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUsername(username);
		return appointmentService.getAll(user);
	}
}
