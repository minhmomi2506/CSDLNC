package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
	Department findDepartmentById(Long id);
}
