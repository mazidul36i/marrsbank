package com.masai.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findByTransactionType(String transactionType);
	
}
