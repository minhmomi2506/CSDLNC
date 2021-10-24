package com.example.QuanLyPhongKham;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyPhongKham.entity.Role;
import com.example.QuanLyPhongKham.repo.RoleRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleTest {
	@Autowired
	private RoleRepo roleRepo;
	
	@Test
	public void testAddRole() {
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("USER");
		Role roleSave = roleRepo.save(role);
		assertThat(roleSave.getId() > 0);
	}
}
