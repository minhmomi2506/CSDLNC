package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.User;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
	public List<Bill> findByUser(User user);

	Bill findBillById(Long id);
	
	@Query(value = "CALL billByMonthAndYear(:month, :year);", nativeQuery = true)
	List<Bill> billByMonthAndYear(@Param("month") int month, @Param("year") int year);
	
	@Query(value = "CALL billByUser(:id);", nativeQuery = true)
	List<Bill> billByUser(@Param("id") Long id);
}
