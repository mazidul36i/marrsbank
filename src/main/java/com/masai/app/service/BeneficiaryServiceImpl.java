package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.AccountException;
import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.exceptions.CustomerException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Beneficiary;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.Customer;
import com.masai.app.model.Wallet;
import com.masai.app.repository.BeneficiaryDao;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.SessionDao;
import com.masai.app.repository.WalletDao;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	CustomerDao customerDao;

	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary, Integer walletId) throws BeneficiaryException {
		
		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("wallet not found"));
		
		beneficiary.setWallet(wallet);

		Optional<Beneficiary> optional = beneficiaryDao.findById(beneficiary.getMobileNumber());

		if (optional.isEmpty()) {
			return beneficiaryDao.save(beneficiary);
		} else {
			throw new BeneficiaryException("Beneficiary Exist");
		}
	}

	@Override
	public Beneficiary deleteBeneficiary(Integer walletId, String mobileNumber) throws BeneficiaryException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("wallet not found"));
		List<Beneficiary> beneficiary = wallet.getBeneficiary();
		Beneficiary bene = null;

		for (int i = 0; i < beneficiary.size(); i++) {
			if (beneficiary.get(i).getMobileNumber().equals(mobileNumber)) {
				bene = beneficiary.remove(i);
			}
		}
		wallet.setBeneficiary(beneficiary);

		bene.setWallet(null);

		beneficiaryDao.delete(bene);

		return bene;

	}

	@Override
	public Beneficiary viewBeneficiary(String mobileNumber) throws BeneficiaryException {
		
		
		Beneficiary bene = beneficiaryDao.findByMobileNumber(mobileNumber);
				
				if(bene == null) new BeneficiaryException("Beneficiary doesn't not found");

		return bene;
		
		
	}
	
	
	
	



	@Override
	public List<Beneficiary> viewAllBeneficiary(Integer customerId) throws BeneficiaryException {
		
		
		
		Customer cust = customerDao.findById(customerId).get();
		if(cust!= null) {
			List<Beneficiary> list = cust.getWallet().getBeneficiary();
			if(list.size()>0) {
				return list;
				
			}else{
				 throw new BeneficiaryException("No beneficiary found");
				 
			}
		}else {
			throw new BeneficiaryException("Customer not found with id "+ customerId);
		}
		
		
		
	
	}

	
	
	
	

}
