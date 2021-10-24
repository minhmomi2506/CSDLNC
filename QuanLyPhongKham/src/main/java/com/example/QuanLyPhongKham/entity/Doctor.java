package com.example.QuanLyPhongKham.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String doctorName;
	
	@Column
	private String gender;
	
	@Column
	private Date dateOfBirth;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String identityCardNumber;
	
	@Column
	private String address;
	
	@Column
	private int salary;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
}
