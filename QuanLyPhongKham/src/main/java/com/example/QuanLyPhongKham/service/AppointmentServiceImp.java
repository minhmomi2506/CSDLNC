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
	
	@Override
	public List<Appointment> getAll() {
		// TODO Auto-generated method stub
		return appointmentRepo.findAllAppointments();
	}
	
	@Override
	public Appointment editAppointment(Long id, Appointment appointment) {
		Appointment appointment1 = appointmentRepo.findAppointmentById(id);
		appointment1.setFullName(appointment.getFullName());
		appointment1.setGender(appointment.getGender());
		appointment1.setDateOfBirth(appointment.getDateOfBirth());
		appointment1.setPhoneNumber(appointment.getPhoneNumber());
		appointment1.setAddress(appointment.getAddress());
		appointment1.setAppointmentDate(appointment.getAppointmentDate());
		appointmentRepo.save(appointment1);
		return appointment1;
		
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		appointmentRepo.deleteAppointment(id);;
	}

}
