package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Tests;

@Service
public interface TestService {
	List<Tests> getAll();
	
	Tests addTest(Tests test);
	
	Tests editTest(Long id, Tests test);
	
	void deleteById(Long id);
}
