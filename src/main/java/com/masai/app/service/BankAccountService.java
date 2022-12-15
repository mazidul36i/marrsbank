package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.AccountException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;

public interface BankAccountService {

	public Wallet addAccount(BankAccount bank, Integer walletId) throws WalletException, AccountException;

	public Wallet deleteAccount(BankAccount bank, Integer walletId) throws WalletException, AccountException;

	public BankAccount viewBankAccountByAccounNo(Integer accNo) throws WalletException, AccountException;

	public List<BankAccount> viewBankAccount(Integer walletId) throws WalletException, AccountException;

}
