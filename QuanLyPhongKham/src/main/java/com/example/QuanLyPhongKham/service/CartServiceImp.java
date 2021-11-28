package com.example.QuanLyPhongKham.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.Cart;
import com.example.QuanLyPhongKham.entity.Medicine;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.BillRepo;
import com.example.QuanLyPhongKham.repo.CartRepo;
import com.example.QuanLyPhongKham.repo.MedicineRepo;

@Component
public class CartServiceImp implements CartService {

	@Autowired
	private MedicineRepo medicineRepo;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private BillRepo billRepo;

	@Override
	public int addMedicine(Long medicineId, int quantity, User user) {
		int addedQuantity = quantity;
		Medicine medicine = medicineRepo.findMedicineById(medicineId);
		Cart cart = cartRepo.findByUserAndMedicine(user, medicine);
		if (cart != null) {
			addedQuantity = cart.getQuantity() + quantity;
			cart.setQuantity(addedQuantity);
			cart.setTotal(cart.getQuantity() * medicine.getPrice());

		} else {
			cart = new Cart();
			cart.setQuantity(quantity);
			cart.setUser(user);
			cart.setMedicine(medicine);
			cart.setTotal(medicine.getPrice() * cart.getQuantity());
		}
		cartRepo.save(cart);
		return addedQuantity;
	}

	@Override
	public List<Cart> getAll(User user) {
		return cartRepo.findByUser(user);
	}

	@Override
	public void checkOut(User user, String address, String phoneNumber) {
		// TODO Auto-generated method stub
		List<Cart> carts = cartRepo.findByUser(user);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		for (Cart cart : carts) {
			Bill bill = new Bill();
			bill.setBuyDate(date);
			bill.setAddress(address);
			bill.setPhoneNumber(phoneNumber);
			bill.setMedicinePrice(cart.getMedicine().getPrice());
			bill.setQuantity(cart.getQuantity());
			bill.setUser(cart.getUser());
			bill.setMedicine(cart.getMedicine());
			bill.setToTal(cart.getTotal());
			billRepo.save(bill);
		}
		cartRepo.deleteByUser(user.getId());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		cartRepo.deleteById(id);
	}

}
