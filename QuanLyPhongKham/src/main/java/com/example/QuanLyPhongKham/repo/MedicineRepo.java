package com.example.QuanLyPhongKham.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
	Medicine findMedicineById(Long id);
	
	@Query(value = "CALL findAllMedicines();", nativeQuery = true)
	List<Medicine> findAllMedicines();
	
	@Query(value = "CALL deleteMedicine(:idDelete);", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteMedicine(@Param("idDelete") Long id);
	
}
