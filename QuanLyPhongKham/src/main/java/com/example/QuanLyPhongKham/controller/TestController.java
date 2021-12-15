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

import com.example.QuanLyPhongKham.entity.Tests;
import com.example.QuanLyPhongKham.service.TestService;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/listTests")
	public List<Tests> listTests(){
		return testService.getAll();
	}
	
	@PostMapping("/addTest")
	public Tests addExamination(@RequestBody Tests test) {
		return testService.addTest(test);
	}
	
	@PutMapping("/editTest/{id}")
	public ResponseEntity<Tests> editTest(@RequestBody Tests test, @PathVariable Long id) {
		return new ResponseEntity<Tests>(testService.editTest(id, test), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public void deleteTest(@PathVariable Long id) {
		testService.deleteById(id);
	}
}
