package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
	Medicine findMedicineById(Long id);
}
