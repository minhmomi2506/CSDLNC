package com.example.QuanLyPhongKham.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Examination;
import com.example.QuanLyPhongKham.repo.ExaminationRepo;

@Component
@Transactional
public class ExaminationServiceImp implements ExaminationService {

	@Autowired
	private ExaminationRepo examinationRepo;

	@Override
	public List<Examination> getAll() {
		// TODO Auto-generated method stub
		return examinationRepo.findAll();
	}

	@Override
	public Examination addExamination(Examination examination) {
		// TODO Auto-generated method stub
		return examinationRepo.save(examination);
	}

	@Override
	public Examination editExamination(Long id, Examination examination) {
		Examination examination1 = examinationRepo.findExaminationById(id);
		examination1.setExaminationName(examination.getExaminationName());
		examination1.setPrice(examination.getPrice());
		examination1.setDescription(examination.getDescription());
		return examinationRepo.save(examination1);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		examinationRepo.deleteById(id);
	}

	@Override
	public Examination findExamination(Long id) {
		// TODO Auto-generated method stub
		return examinationRepo.findExaminationById(id);
	}
}
