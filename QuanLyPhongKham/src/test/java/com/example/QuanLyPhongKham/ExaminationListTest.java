package com.example.QuanLyPhongKham;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyPhongKham.entity.Examination;
import com.example.QuanLyPhongKham.repo.ExaminationRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExaminationListTest {
	@Autowired
	private ExaminationRepo examinationListRepo;
	
	@Test
	public void testAddExaminationList() {
		Examination examinationList = new Examination();
		examinationList.setId(1L);
		examinationList.setExaminationName("Khám dinh dưỡng");
		examinationList.setPrice(500000);
		examinationListRepo.save(examinationList);
		assertThat(examinationList.getId() > 0);
	}
}
