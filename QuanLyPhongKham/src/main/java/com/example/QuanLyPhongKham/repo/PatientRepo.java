package com.example.QuanLyPhongKham.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
	Patient findPatientById(Long id);
	
	@Query(value = "CALL findAllPatients();", nativeQuery = true)
	List<Patient> findAllPatients();
	
	@Query(value = "CALL deletePatient(:idDelete);", nativeQuery = true)
	@Transactional
	@Modifying
	void deletePatient(@Param("idDelete") Long id);
}
