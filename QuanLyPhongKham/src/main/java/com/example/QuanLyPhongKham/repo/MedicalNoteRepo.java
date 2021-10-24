package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.MedicalNote;

@Repository
public interface MedicalNoteRepo extends JpaRepository<MedicalNote, Long>{
	MedicalNote findMedicalNoteById(Long id);
}
