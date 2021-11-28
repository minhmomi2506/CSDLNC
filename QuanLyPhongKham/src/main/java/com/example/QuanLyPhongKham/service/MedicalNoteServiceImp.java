package com.example.QuanLyPhongKham.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.QuanLyPhongKham.entity.Doctor;
import com.example.QuanLyPhongKham.entity.Examination;
import com.example.QuanLyPhongKham.entity.MedicalNote;
import com.example.QuanLyPhongKham.entity.Patient;
import com.example.QuanLyPhongKham.repo.DoctorRepo;
import com.example.QuanLyPhongKham.repo.ExaminationRepo;
import com.example.QuanLyPhongKham.repo.MedicalNoteRepo;
import com.example.QuanLyPhongKham.repo.PatientRepo;

@Component
public class MedicalNoteServiceImp implements MedicalNoteService {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ExaminationRepo examinationRepo;
	
	@Autowired
	private MedicalNoteRepo medicalNoteRepo;

	@Override
	public MedicalNote addMedicalNote(MedicalNote medicalNote, Long patientId, Long doctorId, Long examinationId) {
		Patient patient = patientRepo.findPatientById(patientId);
		Doctor doctor = doctorRepo.findDoctorById(doctorId);
		Examination examination = examinationRepo.findExaminationById(examinationId);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		medicalNote.setDate(date);
		medicalNote.setDoctor(doctor);
		medicalNote.setPatient(patient);
		medicalNote.setExamination(examination);
		return medicalNoteRepo.save(medicalNote);
	}
	
	@Override
	public List<MedicalNote> getAll() {
		return medicalNoteRepo.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int totalMoney(int month, int year) {
		List<MedicalNote> medicalNotes = medicalNoteRepo.findAll();
		int totalMoney = 0;
		for(MedicalNote medicalNote : medicalNotes) {
			if(medicalNote.getDate().getMonth() - month + 1 == 0 && medicalNote.getDate().getYear() - year + 1900 == 0) {
				totalMoney = totalMoney + medicalNote.getTotalMoney();
			}
		}
		return totalMoney;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<MedicalNote> getAllByMonthAndYear(int month, int year) {
		List<MedicalNote> medicalNotes = medicalNoteRepo.findAll();
		List<MedicalNote> medicalNotesStatistic = new ArrayList<MedicalNote>();
		for(MedicalNote medicalNote : medicalNotes) {
			if(medicalNote.getDate().getMonth() - month + 1 == 0 && medicalNote.getDate().getYear() - year + 1900 == 0) {
				medicalNotesStatistic.add(medicalNote);
			}
		}
		return medicalNotesStatistic;
	}

}
