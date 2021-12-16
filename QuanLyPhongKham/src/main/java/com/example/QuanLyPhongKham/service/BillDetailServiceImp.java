package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.BillDetail;
import com.example.QuanLyPhongKham.repo.BillDetailRepo;
import com.example.QuanLyPhongKham.repo.BillRepo;

@Component
public class BillDetailServiceImp implements BillDetailService {
	
	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private BillDetailRepo billDetailRepo;

	@Override
	public List<BillDetail> findByBill(Long id) {
		// TODO Auto-generated method stub
		Bill bill = billRepo.findBillById(id);
		List<BillDetail> billDetails = billDetailRepo.billDetailByBill(bill.getId());
		return billDetails;
	}

}
