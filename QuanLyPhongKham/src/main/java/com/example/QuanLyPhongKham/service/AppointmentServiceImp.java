package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Appointment;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.AppoinmentRepo;

@Component
public class AppointmentServiceImp implements AppointmentService {
	
	@Autowired
	private AppoinmentRepo appointmentRepo;

	@Override
	public Appointment addAppointment(Appointment appointment, User user) {
		// TODO Auto-generated method stub
		appointment.setUser(user);
		return appointmentRepo.save(appointment);
	}
	
	@Override
	public List<Appointment> getAll(User user) {
		// TODO Auto-generated method stub
		return appointmentRepo.findByUser(user);
	}

}
