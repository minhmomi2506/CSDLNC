package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Bill;
import com.example.QuanLyPhongKham.entity.BillDetail;
import com.example.QuanLyPhongKham.entity.Cart;
import com.example.QuanLyPhongKham.entity.Medicine;
import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.BillDetailRepo;
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
	
	@Autowired
	private BillDetailRepo billDetailRepo;

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
		java.util.Date date = new java.util.Date();
		Bill bill = new Bill();
		bill.setBuyDate(date);
		bill.setUser(user);
		billRepo.save(bill);
		bill.setBillId("HOADON"+bill.getId());
		int total = 0;
		for (Cart cart : carts) {
			BillDetail billDetail = new BillDetail();
			bill.setAddress(address);
			bill.setPhoneNumber(phoneNumber);
			billDetail.setMedicinePrice(cart.getMedicine().getPrice());
			billDetail.setQuantity(cart.getQuantity());
			billDetail.setMedicine(cart.getMedicine());
			billDetail.setMoney(cart.getQuantity() * cart.getMedicine().getPrice());
			billDetail.setBill(bill);
			total += cart.getTotal();
			billDetailRepo.save(billDetail);
		}
		bill.setToTal(total);
		billRepo.save(bill);
		cartRepo.deleteByUser(user.getId());
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		cartRepo.deleteById(id);
	}

}
