package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BeneficiaryDetails;

public interface BeneficiaryService {

	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiary, Integer walletId)
			throws WalletException, BeneficiaryException;

	public BeneficiaryDetails deleteBeneficiary(Integer walletId, String mobileNumber)
			throws WalletException, BeneficiaryException;

	public BeneficiaryDetails viewBeneficiary(String mobileNumber) throws BeneficiaryException;

	public List<BeneficiaryDetails> viewAllBeneficiary(Integer customerId) throws WalletException, BeneficiaryException;

}
