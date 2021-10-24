package com.example.QuanLyPhongKham;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.QuanLyPhongKham.entity.Role;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.RoleRepo;
import com.example.QuanLyPhongKham.repo.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserTest {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId(1L);
		user.setUsername("minh");
		user.setPassword("minh");
		Role role = roleRepo.findRoleByRoleName("USER");
		user.setRole(role);
		userRepo.save(user);
		assertThat(user.getId() > 0);
	}
}
