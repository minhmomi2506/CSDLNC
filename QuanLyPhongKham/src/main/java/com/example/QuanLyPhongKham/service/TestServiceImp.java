package com.example.QuanLyPhongKham.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Tests;
import com.example.QuanLyPhongKham.repo.TestsRepo;

@Component
@Transactional
public class TestServiceImp implements TestService {
	@Autowired
	private TestsRepo testsRepo;

	@Override
	public List<Tests> getAll() {
		// TODO Auto-generated method stub
		return testsRepo.findAllTests();
	}
	
	@Override
	public Tests addTest(Tests test) {
		// TODO Auto-generated method stub
		return testsRepo.save(test);
	}
	
	@Override
	public Tests editTest(Long id, Tests test) {
		Tests test1 = testsRepo.findTestsById(id);
		test1.setTestName(test.getTestName());
		test1.setPrice(test.getPrice());
		return testsRepo.save(test1);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		testsRepo.deleteTest(id);
	}

}
