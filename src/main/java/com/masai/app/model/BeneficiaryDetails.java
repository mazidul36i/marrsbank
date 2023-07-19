package com.masai.app.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiaryDetails {

	@Id
	private String mobileNumber;
	private String name;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
}
