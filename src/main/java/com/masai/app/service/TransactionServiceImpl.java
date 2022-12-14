package com.masai.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	public Transaction addTransaction(Transaction transaction, Integer walletId) throws TransactionException, WalletException {
		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
		transaction.setWallet(wallet);
		return transactionDao.save(transaction);
	}

	@Override
	public List<Transaction> viewAllTransactions(Integer walletId) throws WalletException, TransactionException {
		Wallet wallet = walletDao.findById(walletId).orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
		if (wallet.getTransactions() == null || wallet.getTransactions().size() == 0) {
			throw new TransactionException("No transactions have been made on this wallet yet!");
		}
		return wallet.getTransactions();
	}

	@Override
	public List<Transaction> viewTransactionsByDate(LocalDate from, LocalDate to) throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> viewAllTransactionsByType(String type) throws WalletException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
