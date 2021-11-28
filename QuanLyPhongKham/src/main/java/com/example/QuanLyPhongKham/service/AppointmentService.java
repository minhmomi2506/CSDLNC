package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Appointment;
import com.example.QuanLyPhongKham.entity.User;

@Service
public interface AppointmentService {
	Appointment addAppointment(Appointment appointment, User user);
	
	List<Appointment> getAll(User user);
	
	List<Appointment> getAll();
	
	Appointment editAppointment(Long id, Appointment appointment);
	
	void deleteById(Long id);
}
