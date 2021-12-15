package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Appointment;
import com.example.QuanLyPhongKham.entity.User;@Repository
public interface AppoinmentRepo extends JpaRepository<Appointment, Long> {
	List<Appointment> findByUser(User user);
	
	Appointment findAppointmentById(Long id);
	
	@Query(value = "{CALL findAllAppointments()}", nativeQuery = true)
	List<Appointment> findAllAppointments();
}
