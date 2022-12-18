package com.masai.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.TransactionException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.Customer;
import com.masai.app.model.Transaction;
import com.masai.app.model.Wallet;
import com.masai.app.repository.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private CustomerService customerService;;

	@Override
	public Transaction addTransaction(Transaction transaction, String uuid)
			throws TransactionException, WalletException {
		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		transaction.setWallet(wallet);
		return transactionDao.save(transaction);
	}

	@Override
	public List<Transaction> viewAllTransactions(String uuid) throws WalletException, TransactionException {
		Customer customer = customerService.getCustomerByUuid(uuid);

		Wallet wallet = customer.getWallet();
		if (wallet.getTransactions() == null || wallet.getTransactions().size() == 0) {
			throw new TransactionException("No transactions have been made on this wallet yet!");
		}
		return wallet.getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionsByDate(LocalDate from, LocalDate to, String uuid)
			throws WalletException, TransactionException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		List<Transaction> transactions = wallet.getTransactions().stream().filter(transaction -> {
			int diff1 = transaction.getTransactionDate().compareTo(from);
			int diff2 = transaction.getTransactionDate().compareTo(to);
			return diff1 >= 0 && diff2 <= 0;
		}).toList();

		if (transactions == null || transactions.size() == 0)
			throw new TransactionException("No transactions found to be load!");
		return transactions;
	}

	@Override
	public List<Transaction> viewAllTransactionsByType(String type, String uuid)
			throws WalletException, TransactionException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		Wallet wallet = customer.getWallet();

		List<Transaction> transactions = wallet.getTransactions().stream()
				.filter(transaction -> transaction.getTransactionType().toLowerCase().equals(type.toLowerCase()))
				.toList();

		if (transactions == null || transactions.size() == 0)
			throw new TransactionException("No transactions exists by type: " + type);
		return transactions;
	}

}
