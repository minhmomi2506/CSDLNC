package com.example.QuanLyPhongKham.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Cart;
import com.example.QuanLyPhongKham.entity.Medicine;
import com.example.QuanLyPhongKham.entity.User;

@Repository
@Transactional
public interface CartRepo extends JpaRepository<Cart, Long> {
	List<Cart> findByUser(User user);

	Cart findByUserAndMedicine(User user, Medicine medicine);

	@Query("delete from Cart c where c.user.id = ?1 ")
	@Modifying
	void deleteByUser(Long userId);
}
