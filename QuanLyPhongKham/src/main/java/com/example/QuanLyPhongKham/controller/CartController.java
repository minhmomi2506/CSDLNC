package com.example.QuanLyPhongKham.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuanLyPhongKham.entity.Cart;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.UserRepo;
import com.example.QuanLyPhongKham.service.CartService;


@RestController
public class CartController {
	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepo userRepo;

	/* ADD MEDICINE TO CART */
	@PostMapping("/addMedicineToCart/{pid}/{qty}")
	public String addToCart(@PathVariable("pid") Long id, @PathVariable("qty") int quantity, Model model,
			Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUsername(username);
		int addedQuantity = cartService.addMedicine(id, quantity, user);
		return addedQuantity + "";
	}
	
	@GetMapping("/listCart")
	public List<Cart> getAll(Principal principal){
		String username = principal.getName();
		User user = userRepo.getUserByUsername(username);
		return cartService.getAll(user);
	}
	
	@DeleteMapping("/deleteCart/{id}")
	public void deleteExamination(@PathVariable Long id) {
		cartService.deleteById(id);
	}
}
