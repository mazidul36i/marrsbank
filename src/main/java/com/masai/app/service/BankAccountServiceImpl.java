package com.masai.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.AccountException;
import com.masai.app.exceptions.TransactionException;
import com.masai.app.exceptions.WalletException;
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
	public Wallet addAccount(BankAccount bank, Integer walletId) throws WalletException, AccountException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("wallet not found"));
		bank.setWallet(wallet);

		BankAccount account = bDao.save(bank);
		return wallet;

	}

	@Override
	public Wallet deleteAccount(BankAccount bank, Integer walletId) throws WalletException, AccountException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("wallet not found"));
		List<BankAccount> accounts = wallet.getBankAccounts();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNo() == bank.getAccountNo()) {
				BankAccount account = accounts.remove(i);
			}
		}
		wallet.setBankAccounts(accounts);

		bank.setWallet(null);

		bDao.delete(bank);

		return wallet;

	}

	@Override
	public List<BankAccount> viewBankAccount(Integer walletId) throws WalletException, AccountException {

		Wallet wallet = walletDao.findById(walletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't not found!"));

		if (wallet.getBankAccounts() == null || wallet.getBankAccounts().size() == 0) {
			throw new AccountException("No Account Found");
		}

		return wallet.getBankAccounts();

	}

	@Override
	public BankAccount viewBankAccountByAccounNo(Integer accNo) throws WalletException, AccountException {

		BankAccount account = bDao.findById(accNo)
				.orElseThrow(() -> new AccountException("Account doesn't not found with accNo: " + accNo));

		return account;

	}

}
