package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Medicine;

@Service
public interface MedicineService {
	List<Medicine> getAll();
}
