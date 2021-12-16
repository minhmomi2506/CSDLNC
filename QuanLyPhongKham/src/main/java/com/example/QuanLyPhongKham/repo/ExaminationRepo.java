package com.example.QuanLyPhongKham.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Examination;

@Repository
public interface ExaminationRepo extends JpaRepository<Examination, Long> {
	Examination findExaminationById(Long id);
	
	@Query(value = "CALL findAllExaminations();", nativeQuery = true)
	List<Examination> findAllExaminations();
	
	@Query(value = "CALL deleteExamination(:idDelete);", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteExamination(@Param("idDelete") Long id);
}
