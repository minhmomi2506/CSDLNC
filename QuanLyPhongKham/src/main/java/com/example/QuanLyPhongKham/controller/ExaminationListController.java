package com.example.QuanLyPhongKham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.Examination;
import com.example.QuanLyPhongKham.service.ExaminationService;

@RestController
public class ExaminationListController {
	@Autowired
	private ExaminationService examinationListService;

	
	@PostMapping("/addExamination")
	public ResponseEntity<Examination> addExamination(@RequestBody Examination examination) {
		return new ResponseEntity<Examination>(examinationListService.addExamination(examination), HttpStatus.OK);
	}
	
	@GetMapping("/listExaminations")
	public List<Examination> getAll(){
		return examinationListService.getAll();
	}
	
	@PutMapping("/editExamination/{id}")
	public ResponseEntity<Examination> editExamination(@RequestBody Examination examination, @PathVariable Long id) {
		return new ResponseEntity<Examination>(examinationListService.editExamination(id, examination), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteExamination/{id}")
	public void deleteExamination(@PathVariable Long id) {
		examinationListService.deleteById(id);
	}
	
	@GetMapping("/findExamination/{id}")
	public ResponseEntity<Examination> addExamination(@PathVariable Long id) {
		return new ResponseEntity<Examination>(examinationListService.findExamination(id), HttpStatus.OK);
	} 
}
