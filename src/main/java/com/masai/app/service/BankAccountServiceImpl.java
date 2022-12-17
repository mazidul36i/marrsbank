package com.masai.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.AccountException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Customer;
import com.masai.app.model.Wallet;
import com.masai.app.repository.BankAccountDao;
import com.masai.app.repository.WalletDao;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountDao bDao;

	@Autowired
	private CustomerService customerService;

	@Override
	public BankAccount addAccount(BankAccount bank, String uuid) throws WalletException, AccountException {
		Customer customer = customerService.getCustomerByUuid(uuid);
		bank.setWallet(customer.getWallet());
		return bDao.save(bank);
	}

	@Override
	public Wallet deleteAccount(String accNo, String uuid) throws WalletException, AccountException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		Wallet wallet = customer.getWallet();
		BankAccount account = null;
		List<BankAccount> accounts = wallet.getBankAccounts();
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccountNo().equals(accNo)) {
				account = accounts.remove(i);
			}
		}
		if (account == null)
			throw new AccountException("Account doesn't found!");

		wallet.setBankAccounts(accounts);

		account.setWallet(null);

		bDao.delete(account);

		return wallet;

	}

	@Override
	public List<BankAccount> viewBankAccount(String uuid) throws WalletException, AccountException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		if (wallet.getBankAccounts() == null || wallet.getBankAccounts().size() == 0) {
			throw new AccountException("No bank account added yet!");
		}

		return wallet.getBankAccounts();
	}

	@Override
	public BankAccount viewBankAccountByAccounNo(String accNo, String uuid) throws WalletException, AccountException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		BankAccount account = null;

		for (BankAccount bankAccount : customer.getWallet().getBankAccounts()) {
			if (bankAccount.getAccountNo().equals(accNo)) {
				account = bankAccount;
				break;
			}
		}

		if (account == null)
			throw new AccountException("Account doesn't not found with account number: " + accNo);
		return account;
	}

}
