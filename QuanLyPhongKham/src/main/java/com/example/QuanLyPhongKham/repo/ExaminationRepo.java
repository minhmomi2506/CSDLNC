package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Examination;

@Repository
public interface ExaminationRepo extends JpaRepository<Examination, Long> {
	Examination findExaminationById(Long id);
	
	@Query(value = "CALL findAllExaminations();", nativeQuery = true)
	List<Examination> findAllExaminations();
}
