package com.example.QuanLyPhongKham.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.QuanLyPhongKham.entity.User;
import com.example.QuanLyPhongKham.repo.UserRepo;
import com.example.QuanLyPhongKham.service.UserService;

@Controller
public class PhongKhamController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;

	/*LOGIN*/
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, Model model) {
		Authentication authen = SecurityContextHolder.getContext().getAuthentication();
		if (authen == null || authen instanceof AnonymousAuthenticationToken || request.getSession() == null) {
			return "login/loginPage";
		}
		model.addAttribute("user", user);
		request.getSession().setAttribute("MY_SESSION_MESSAGES", user);
		return "redirect:/";
	}
	
	/*LOG OUT*/
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	/*REGISTER ACCOUNT*/
	@PostMapping("/save")
	public String save(User user) {
		userService.saveUserToDB(user);
		return "redirect:/login";
	}

	/*HOME PAGE*/
	@RequestMapping("/")
	public String homePage(Model model, Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByUsername(username);
		model.addAttribute("user", user);
		if (user.getRole().getRoleName().equalsIgnoreCase("admin")) {
			model.addAttribute("role", user.getRole().getRoleName());
			return "admin/other/homePage";
		} else {
			return "user/other/homePage";
		}
	}
	
	/*LIST EXAMINATIONS*/
	@GetMapping("/examination")
	public String listExamination() {
		return "admin/examinationList/examinationList";
	}
	
	/*LIST TESTS*/
	@GetMapping("/test")
	public String listTest() {
		return "admin/testList/testList";
	}
	
	/*LIST PATIENTS*/
	@GetMapping("/patient")
	public String patient() {
		return "admin/patient/patientList";
	}
	
	/*LIST DOCTORS*/
	@GetMapping("/doctor")
	public String doctor() {
		return  "admin/doctor/doctorList";
	}
	
	/*LIST MEDICAL NOTES*/
	@GetMapping("/medicalNote")
	public String medicalNote() {
		return  "admin/medicalNote/medicalNoteList";
	}
	
	/*LIST DEPARTMENTS*/
	@GetMapping("/department")
	public String department() {
		return "admin/department/departmentList";
	}
	
	/*LIST MEDICINES*/
	@GetMapping("/medicine")
	public String medicine() {
		return "admin/medicine/medicineList";
	}
	
	
	/*USER CONTROLLER*/
	/*LIST APPOINTMENT*/
	@GetMapping("/yourAppointment")
	public String appointment() {
		return "user/appointment/appointmentList";
	}
	
	/*LIST MEDICINE*/
	@GetMapping("/yourMedicine")
	public String yourMedicine() {
		return "user/medicine/medicineList";
	}
}
