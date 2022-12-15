package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.Beneficiary;


public interface BeneficiaryService {
	
    public Beneficiary addBeneficiary(Beneficiary beneficiary) throws BeneficiaryException;
	
	public Beneficiary deleteBeneficiary(String key) throws BeneficiaryException;
	
	public Beneficiary viewBeneficiary(String name,String key) throws BeneficiaryException;
	
	public List<Beneficiary> viewAllBeneficiary(String key)throws BeneficiaryException;

	

}
