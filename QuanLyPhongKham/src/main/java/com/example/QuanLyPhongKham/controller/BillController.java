package com.example.QuanLyPhongKham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.service.BillService;

@RestController
public class BillController {
	@Autowired
	private BillService billService;
	
	/* STATISTICS */
	@GetMapping("/statistic/{month}/{year}")
	public int statistic(@PathVariable int month, @PathVariable int year) {
		int totalMoney = billService.totalMoney(month, year);
		return totalMoney;
	}

	/* BILLS BY MONTH AND YEAR */
	@GetMapping("/billsByMonthAndYear/{month}/{year}")
	public List<Bill> billsByMonthAndYear(@PathVariable int month, @PathVariable int year) {
		List<Bill> billsByMonthAndYear = billService.getAllByMonthAndYear(month, year);
		return billsByMonthAndYear;
	}
}
