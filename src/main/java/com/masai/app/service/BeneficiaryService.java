package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BeneficiaryDetails;

public interface BeneficiaryService {

	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiary, String uuid)
			throws WalletException, BeneficiaryException;

	public BeneficiaryDetails deleteBeneficiary(String uuid, String mobileNumber)
			throws WalletException, BeneficiaryException;

	public BeneficiaryDetails viewBeneficiary(String uuid, String mobileNumber) throws BeneficiaryException;

	public List<BeneficiaryDetails> viewAllBeneficiary(String uuid) throws WalletException, BeneficiaryException;

}
