package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BeneficiaryDetails;
import com.masai.app.model.Wallet;
import com.masai.app.repository.BeneficiaryDao;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.WalletDao;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	private BeneficiaryDao beneficiaryDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	CustomerDao customerDao;

	@Override
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiary, Integer walletId)
			throws WalletException, BeneficiaryException {

		Wallet wallet = walletDao.findById(walletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
		Optional<BeneficiaryDetails> optional = beneficiaryDao.findById(beneficiary.getMobileNumber());

		beneficiary.setWallet(wallet);

		if (optional.isEmpty()) {
			return beneficiaryDao.save(beneficiary);
		} else {
			throw new BeneficiaryException("Beneficiary already exists!");
		}
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(Integer walletId, String mobileNumber)
			throws WalletException, BeneficiaryException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("Wallet doesn't found!"));
		List<BeneficiaryDetails> beneficiaries = wallet.getBeneficiaryDetails();
		BeneficiaryDetails beneficiary = null;

		for (int i = 0; i < beneficiaries.size(); i++) {
			if (beneficiaries.get(i).getMobileNumber().equals(mobileNumber)) {
				beneficiary = beneficiaries.remove(i);
			}
		}
		
		if (beneficiary == null) throw new BeneficiaryException("No beneficiary exists with mobile number: " + mobileNumber);

		wallet.setBeneficiaryDetails(beneficiaries);
		beneficiary.setWallet(null);
		
		beneficiaryDao.delete(beneficiary);
		return beneficiary;
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber) throws BeneficiaryException {
		BeneficiaryDetails beneficiary = beneficiaryDao.findById(mobileNumber)
				.orElseThrow(() -> new BeneficiaryException("Beneficiary doesn't not found with mobile number: " + mobileNumber));
		return beneficiary;
	}

	@Override
	public List<BeneficiaryDetails> viewAllBeneficiary(Integer walletId) throws WalletException, BeneficiaryException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("Wallet doesn't found!"));

		if (wallet.getBeneficiaryDetails() == null || wallet.getBeneficiaryDetails().size() == 0) {
			throw new WalletException("No beneficiary found to be load!");
		} else {
			return wallet.getBeneficiaryDetails();
		}

	}

}
