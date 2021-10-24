package com.example.QuanLyPhongKham.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuanLyPhongKham.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findRoleByRoleName(String roleName);

}
