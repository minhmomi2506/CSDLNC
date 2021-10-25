package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.MedicalNote;

@Service
public interface MedicalNoteService {
	MedicalNote addMedicalNote(MedicalNote medicalNote,Long patientId, Long doctorId, Long examinationId);
	
	List<MedicalNote> getAll();
}
