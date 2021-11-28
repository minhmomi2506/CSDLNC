package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuanLyPhongKham.entity.Cart;
import com.example.QuanLyPhongKham.entity.User;

@Service
public interface CartService {
	int addMedicine(Long medicineId , int quantity , User user);
	
	List<Cart> getAll(User user);
	
	void checkOut(User user , String address , String phoneNumber);
	
	void deleteById(Long id);
}
