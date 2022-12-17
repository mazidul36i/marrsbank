package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BeneficiaryDetails;
import com.masai.app.model.Customer;
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

	@Autowired
	private CustomerService customerService;

	@Override
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiary, String uuid)
			throws WalletException, BeneficiaryException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		Wallet wallet = customer.getWallet();
		Optional<BeneficiaryDetails> optional = beneficiaryDao.findById(beneficiary.getMobileNumber());

		beneficiary.setWallet(wallet);

		if (optional.isEmpty()) {
			return beneficiaryDao.save(beneficiary);
		} else {
			throw new BeneficiaryException("Beneficiary already exists!");
		}
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(String uuid, String mobileNumber)
			throws WalletException, BeneficiaryException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		List<BeneficiaryDetails> beneficiaries = wallet.getBeneficiaryDetails();
		BeneficiaryDetails beneficiary = null;

		for (int i = 0; i < beneficiaries.size(); i++) {
			if (beneficiaries.get(i).getMobileNumber().equals(mobileNumber)) {
				beneficiary = beneficiaries.remove(i);
			}
		}

		if (beneficiary == null)
			throw new BeneficiaryException("No beneficiary exists with mobile number: " + mobileNumber);

		wallet.setBeneficiaryDetails(beneficiaries);
		beneficiary.setWallet(null);

		beneficiaryDao.delete(beneficiary);
		return beneficiary;
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber, String uuid) throws BeneficiaryException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();
		BeneficiaryDetails beneficiaryDetails = null;

		for (BeneficiaryDetails beneficiary : wallet.getBeneficiaryDetails()) {
			if (beneficiary.getMobileNumber().equals(mobileNumber)) {
				beneficiaryDetails = beneficiary;
				break;
			}
		}

		if (beneficiaryDetails == null)
			throw new BeneficiaryException("Beneficiary doesn't not found with mobile number: " + mobileNumber);
		return beneficiaryDetails;
	}

	@Override
	public List<BeneficiaryDetails> viewAllBeneficiary(String uuid) throws WalletException, BeneficiaryException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		if (wallet.getBeneficiaryDetails() == null || wallet.getBeneficiaryDetails().size() == 0) {
			throw new WalletException("No beneficiary found to be load!");
		} else {
			return wallet.getBeneficiaryDetails();
		}

	}

}
