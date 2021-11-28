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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Medicine medicine;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column
	private int quantity;
	
	@Column
	private String phoneNumber;
	
	@Column
	private int medicinePrice;
	
	@Column
	private int toTal;
	
	@Column
	private Date buyDate;
	
	@Column
	private String address;
}