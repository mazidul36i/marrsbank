package com.masai.app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Double balance;

//	@JoinColumn(name = "wallet_id")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "wallet")
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BankAccount> bankAccounts = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<Transaction> transactions = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BillPayment> billPayments = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "wallet")
	private List<BeneficiaryDetails> beneficiaryDetails = new ArrayList<>();
}
