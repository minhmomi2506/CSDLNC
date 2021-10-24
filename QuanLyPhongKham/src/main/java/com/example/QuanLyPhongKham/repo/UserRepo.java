package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User getUserByUsername(String username);
}
