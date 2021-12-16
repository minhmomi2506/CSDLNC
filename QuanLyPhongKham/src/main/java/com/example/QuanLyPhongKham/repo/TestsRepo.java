package com.example.QuanLyPhongKham.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Tests;

@Repository
@Transactional
public interface TestsRepo extends JpaRepository<Tests, Long> {
	Tests findTestsById(Long id);

	@Query(value = "{CALL findAllTests()}", nativeQuery = true)
	public List<Tests> findAllTests();

	@Query(value = "CALL deleteTest(:idDelete);", nativeQuery = true)
	@Transactional
	@Modifying
	public void deleteTest(@Param("idDelete") Long id);
}
