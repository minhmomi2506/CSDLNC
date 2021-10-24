package com.example.QuanLyPhongKham.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/listUsers")
	public List<User> listAllUsers(){
		List<User> users = userService.findAll();
		return users;
	}
}
