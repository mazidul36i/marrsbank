package com.masai.app.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPayment {

	private Integer billId;
	private String billType;
	private Double amount;
	private LocalDate paymentDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
	
	
}
