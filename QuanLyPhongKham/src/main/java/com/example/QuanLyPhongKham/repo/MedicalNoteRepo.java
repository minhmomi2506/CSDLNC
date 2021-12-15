package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.MedicalNote;

@Repository
public interface MedicalNoteRepo extends JpaRepository<MedicalNote, Long>{
	MedicalNote findMedicalNoteById(Long id);
	
	@Query(value = "CALL findAllMedicalNotes();", nativeQuery = true)
	List<MedicalNote> findAllMedicalNotes();
}
