package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Tests;

@Repository
public interface TestsRepo extends JpaRepository<Tests, Long> {
	Tests findTestsById(Long id);
}
