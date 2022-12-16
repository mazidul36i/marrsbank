package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.Beneficiary;
import com.masai.app.model.Customer;
import com.masai.app.model.Wallet;


public interface BeneficiaryService {
	
    public Beneficiary addBeneficiary(Beneficiary beneficiary, Integer walletId) throws BeneficiaryException;
	
	public Beneficiary deleteBeneficiary(Integer walletId, String mobileNumber) throws BeneficiaryException;
	
	public Beneficiary viewBeneficiary(String mobileNumber) throws BeneficiaryException;
	
	public List<Beneficiary> viewAllBeneficiary(Integer customerId)throws BeneficiaryException;

}
