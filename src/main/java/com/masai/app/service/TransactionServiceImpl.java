package com.masai.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.TransactionException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.Transaction;
import com.masai.app.model.Wallet;
import com.masai.app.repository.TransactionDao;
import com.masai.app.repository.WalletDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private WalletDao walletDao;

	@Override
	public Transaction addTransaction(Transaction transaction, Integer walletId)
			throws TransactionException, WalletException {
		Wallet wallet = walletDao.findById(walletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
		transaction.setWallet(wallet);
		return transactionDao.save(transaction);
	}

	@Override
	public List<Transaction> viewAllTransactions(Integer walletId) throws WalletException, TransactionException {
		Wallet wallet = walletDao.findById(walletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
		if (wallet.getTransactions() == null || wallet.getTransactions().size() == 0) {
			throw new TransactionException("No transactions have been made on this wallet yet!");
		}
		return wallet.getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionsByDate(LocalDate from, LocalDate to, Integer walletId)
			throws WalletException, TransactionException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(null);

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
	public List<Transaction> viewAllTransactionsByType(String type, Integer walletId)
			throws WalletException, TransactionException {

		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("Wallet doesn't exists!"));

		List<Transaction> transactions = wallet.getTransactions().stream()
				.filter(transaction -> transaction.getTransactionType().toLowerCase().equals(type.toLowerCase()))
				.toList();

		if (transactions == null || transactions.size() == 0)
			throw new TransactionException("No transactions exists by type: " + type);
		return transactions;
	}

}
