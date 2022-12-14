package com.masai.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.AccountException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;
import com.masai.app.repository.BankAccountDao;
import com.masai.app.repository.WalletDao;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    
	@Autowired
	private BankAccountDao bDao;
	@Autowired
	private WalletDao walletDao;   

	
	@Override
	public Wallet addAccount(BankAccount bank,Integer walletId)  {
		
		Wallet wallet=walletDao.findById(walletId).orElseThrow(()->new RuntimeException("wallet not found"));
		bank.setWallet(wallet);
			
		BankAccount account = bDao.save(bank);
        return wallet;
		
		
	}


	
	
	@Override
	public Wallet deleteAccount(BankAccount bank) {

		BankAccount account=bDao.findById(bank.getAccountNo()).orElseThrow(()->new AccountException("Bank not found"));
		bDao.delete(account);
		return account.getWallet();
		
		
		
	}
	
	
	

}
