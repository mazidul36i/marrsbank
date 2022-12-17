package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.AccountException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;

public interface BankAccountService {

	public BankAccount addAccount(BankAccount bank, String uuid) throws WalletException, AccountException;

	public Wallet deleteAccount(String accNo, String uuid) throws WalletException, AccountException;

	public BankAccount viewBankAccountByAccounNo(String accNo, String uuid) throws WalletException, AccountException;

	public List<BankAccount> viewBankAccount(String uuid) throws WalletException, AccountException;

}
