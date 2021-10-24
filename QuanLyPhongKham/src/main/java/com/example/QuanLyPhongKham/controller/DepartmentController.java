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

import com.example.QuanLyPhongKham.entity.Department;
import com.example.QuanLyPhongKham.service.DepartmentService;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/listDepartments")
	public List<Department> getAll(){
		return departmentService.getAll();
	}
	
	@PostMapping("/addDepartment")
	public ResponseEntity<Department> addDoctor(@RequestBody Department department) {
		return new ResponseEntity<Department>(departmentService.addDepartment(department), HttpStatus.OK);
	} 
	
	@PutMapping("/editDepartment/{id}")
	public ResponseEntity<Department> editExamination(@RequestBody Department department, @PathVariable Long id) {
		return new ResponseEntity<Department>(departmentService.editDepartment(id, department), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public void deleteDepartment(@PathVariable Long id) {
		departmentService.deleteById(id);
	}
}
