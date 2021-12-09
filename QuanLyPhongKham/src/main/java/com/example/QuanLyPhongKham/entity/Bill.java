package com.example.QuanLyPhongKham.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table
@Data
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String billId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String phoneNumber;

	@Column
	private int toTal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private java.util.Date buyDate;

	@PrePersist
	private void onCreate() {
		buyDate = new Date();
	}

	@Column
	private String address;

	@JsonManagedReference
	@OneToMany(mappedBy = "bill", cascade = { CascadeType.ALL })
	private List<BillDetail> billDetails;
}
