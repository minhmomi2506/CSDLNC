package com.example.QuanLyPhongKham.entity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table
@Data
public class BillDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Medicine medicine;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "bill_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Bill bill;

	@Column
	private int medicinePrice;

	@Column
	private int quantity;

	@Column
	private int money;
}
