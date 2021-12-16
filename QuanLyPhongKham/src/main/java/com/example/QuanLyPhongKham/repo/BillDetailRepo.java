package com.example.QuanLyPhongKham.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.BillDetail;

@Repository
public interface BillDetailRepo extends JpaRepository<BillDetail, Long> {
	List<BillDetail> findByBill(Bill bill);
	
	@Query(value = "CALL billDetailByBill(:id);", nativeQuery = true)
	List<BillDetail> billDetailByBill(@Param("id") Long id);
}
