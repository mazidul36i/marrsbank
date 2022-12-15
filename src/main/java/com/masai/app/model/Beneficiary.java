package com.masai.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {

	private String name;
	@Id
	private String mobileNumber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
}
