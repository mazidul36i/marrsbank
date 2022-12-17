package com.masai.app.service;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.exceptions.InsufficientBalanceException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.CustomerDTO;

public interface WalletService {

	public CustomerDTO showBalance(String uuid) throws CustomerException;

	public String fundTransafer(String uuid, String targetMobileNumber, Double amount)
			throws WalletException, InsufficientBalanceException;

	public CustomerDTO depositAmount(String uuid, Double amount) throws WalletException;

}
