package com.example.QuanLyPhongKham;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyPhongKham.entity.Tests;
import com.example.QuanLyPhongKham.repo.TestsRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestListTest {
	@Autowired
	private TestsRepo testListRepo;
	
	@Test
	public void testAddTest() {
		Tests testList = new Tests();
		testList.setId(1L);
		testList.setTestName("Máu");
		testList.setPrice(50000);
		testListRepo.save(testList);
		assertThat(testList.getId() > 0);
	}
}
