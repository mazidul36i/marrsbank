package com.masai.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer walletId;
	private Long balance;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<BankAccount> bankAccounts = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<Transaction> transactions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<BillPayment> billPayments = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Set<BeneficiaryDetails> beneficiaryDetails = new HashSet<>();
}
