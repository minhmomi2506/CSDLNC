package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	Doctor findDoctorById(Long id);

	@Query(value = "{CALL findAllDoctors()}", nativeQuery = true)
	List<Doctor> findAllDoctors();
}
