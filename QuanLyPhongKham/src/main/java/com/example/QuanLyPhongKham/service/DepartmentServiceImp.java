package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Department;
import com.example.QuanLyPhongKham.repo.DepartmentRepo;

@Component
public class DepartmentServiceImp implements DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll();
	}
	
	@Override
	public Department addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentRepo.save(department);
	}
	
	@Override
	public Department editDepartment(Long id, Department department) {
		Department department1 = departmentRepo.findDepartmentById(id);
		department1.setDepartmentName(department.getDepartmentName());
		return departmentRepo.save(department1);
	}
	
	@Override
	public void deleteById(Long id) {
		departmentRepo.deleteById(id);
	}

}
