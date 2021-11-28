package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.User;


@Service
public interface BillService {
	List<Bill> getAll(User user);
	
	int totalMoney(int month , int year);
	
	List<Bill> getAllByMonthAndYear(int month , int year);
}
