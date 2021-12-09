package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.BillDetail;

@Service
public interface BillDetailService {
	List<BillDetail> findByBill(Long id);
}
