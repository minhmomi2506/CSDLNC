package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Examination;

@Repository
public interface ExaminationRepo extends JpaRepository<Examination, Long> {
	Examination findExaminationById(Long id);
}
