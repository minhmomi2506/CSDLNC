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

import com.example.QuanLyPhongKham.entity.Medicine;
import com.example.QuanLyPhongKham.service.MedicineService;

@RestController
public class MedicineController {
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/listMedicines")
	public List<Medicine> getAll(){
		return medicineService.getAll();
	}
	
	@PostMapping("/addMedicine")
	public ResponseEntity<Medicine> addDoctor(@RequestBody Medicine medicine) {
		return new ResponseEntity<Medicine>(medicineService.addMedicine(medicine), HttpStatus.OK);
	} 
	
	@PutMapping("/editMedicine/{id}")
	public ResponseEntity<Medicine> editMedicine(@RequestBody Medicine medicine, @PathVariable Long id) {
		return new ResponseEntity<Medicine>(medicineService.editMedicine(id, medicine), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMedicine/{id}")
	public void deleteMedicine(@PathVariable Long id) {
		medicineService.deleteById(id);
	}
}
