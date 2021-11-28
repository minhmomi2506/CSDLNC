package com.example.QuanLyPhongKham;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyPhongKham.entity.Status;
import com.example.QuanLyPhongKham.repo.StatusRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StatusTest {
	@Autowired
	private StatusRepo statusRepo;
	
	@Test
	public void testAddStatus() {
		Status status = new Status();
		status.setId(5L);
		status.setStatusName("Hủy đơn");
		statusRepo.save(status);
		assertThat(status.getId() > 0);
	}
}
