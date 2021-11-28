package com.example.QuanLyPhongKham.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.BillRepo;

@Component
public class BillServiceImp implements BillService {
	
	@Autowired
	private BillRepo billRepo;

	@Override
	public List<Bill> getAll(User user) {
		// TODO Auto-generated method stub
		return billRepo.findByUser(user);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int totalMoney(int month, int year) {
		// TODO Auto-generated method stub
		List<Bill> bills = billRepo.findAll();
		int totalMoney = 0;
		for(Bill bill : bills) {
			if(bill.getBuyDate().getMonth() - month + 1 == 0 && bill.getBuyDate().getYear() - year + 1900 == 0) {
				totalMoney = totalMoney + bill.getToTal();
			}
		}
		return totalMoney;
	}
	
	@SuppressWarnings("deprecation")
	@Override
		public List<Bill> getAllByMonthAndYear(int month , int year) {
			List<Bill> bills = billRepo.findAll();
			List<Bill> billsByMonthAndYear = new ArrayList<Bill>();
			for(Bill bill : bills) {
				if(bill.getBuyDate().getMonth() - month + 1 == 0 && bill.getBuyDate().getYear() - year + 1900 == 0) {
					billsByMonthAndYear.add(bill);
				}
			}
			return billsByMonthAndYear;
			
		}

}
