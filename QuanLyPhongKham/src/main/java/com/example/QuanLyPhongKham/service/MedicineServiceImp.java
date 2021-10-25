package com.example.QuanLyPhongKham.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Medicine;
import com.example.QuanLyPhongKham.repo.MedicineRepo;

@Component
public class MedicineServiceImp implements MedicineService {
	
	@Autowired
	private MedicineRepo medicineRepo;

	@Override
	public List<Medicine> getAll() {
		return medicineRepo.findAll();
	}

	@Override
	public Medicine addMedicine(Medicine medicine) {
		return medicineRepo.save(medicine);
	}
	
	@Override
	public Medicine editMedicine(Long id, Medicine medicine) {
		Medicine medicine1 = medicineRepo.findMedicineById(id);
		medicine1.setMedicineName(medicine.getMedicineName());
		medicine1.setPrice(medicine.getPrice());
		return medicineRepo.save(medicine1);
	}
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		medicineRepo.deleteById(id);
	}
}
