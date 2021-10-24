package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Department;

@Service
public interface DepartmentService {
	List<Department> getAll();
	
	Department addDepartment(Department department);
	
	Department editDepartment(Long id, Department department);
	
	void deleteById(Long id);
}
