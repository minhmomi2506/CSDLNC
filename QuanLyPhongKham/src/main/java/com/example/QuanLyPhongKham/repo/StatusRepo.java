package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {
	Status findStatusById(Long id);

	Status findStatusByStatusName(String statusName);
}
