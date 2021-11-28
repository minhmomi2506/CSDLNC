package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.User;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
	public List<Bill> findByUser(User user);

	Bill findBillById(Long id);
}