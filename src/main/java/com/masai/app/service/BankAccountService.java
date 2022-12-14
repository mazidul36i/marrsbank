package com.masai.app.service;



import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;


public interface BankAccountService {
	
	public Wallet addAccount(BankAccount bank,Integer walletId);
	public Wallet deleteAccount(BankAccount bank);

}
