package com.example.QuanLyPhongKham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.MedicalNote;
import com.example.QuanLyPhongKham.service.MedicalNoteService;

@RestController
public class MedicalNoteController {
	@Autowired
	private MedicalNoteService medicalNoteService;

	@PostMapping("/addMedicalNote/{patientId}/{doctorId}/{examinationId}")
	public ResponseEntity<MedicalNote> addDoctor(@RequestBody MedicalNote medicalNote, @PathVariable Long patientId,
			@PathVariable Long doctorId, @PathVariable Long examinationId) {
		return new ResponseEntity<MedicalNote>(medicalNoteService.addMedicalNote(medicalNote, patientId, doctorId, examinationId), HttpStatus.OK);
	}
	
	@GetMapping("/listMedicalNotes")
	public List<MedicalNote> getAll(){
		return medicalNoteService.getAll();
	}
}
