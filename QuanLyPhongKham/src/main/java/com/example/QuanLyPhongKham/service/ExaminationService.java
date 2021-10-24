package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Examination;

@Service
public interface ExaminationService {
	 List<Examination> getAll();
	 
	 Examination addExamination(Examination examination);
	 
	 Examination editExamination(Long id, Examination examination);
	 
	 void deleteById(Long id);
	 
	 Examination findExamination(Long id);
}
