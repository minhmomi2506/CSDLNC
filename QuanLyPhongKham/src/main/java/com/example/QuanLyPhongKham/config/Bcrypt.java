package com.example.QuanLyPhongKham.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
	public static void main(String[] args) {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String abc = encode.encode("lan");
		System.out.println(abc);

	}
}
