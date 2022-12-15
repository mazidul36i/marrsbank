package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.Beneficiary;
import com.masai.app.repository.BeneficiaryDao;



@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
	
	
	
	@Autowired
	BeneficiaryDao beneficiaryDao;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary) throws BeneficiaryException {
		
		
       Optional<Beneficiary> optional=beneficiaryDao.findById(beneficiary.getMobileNumber());
		
		if(optional.isEmpty()) {
			return beneficiaryDao.save(beneficiary);
		}
		else {
			throw new BeneficiaryException("Already Beneficiary Exist");
		}
	}
	
	
	
	

	@Override
	public Beneficiary deleteBeneficiary(String key) throws BeneficiaryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Beneficiary viewBeneficiary(String name, String key) throws BeneficiaryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	
	


	

}
