package com.masai.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.app.model.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

	@Query("SELECT t FROM Transaction t WHERE t.transactionDate BETWEEN :from AND :to")
	public List<Transaction> getTransactionsByDate(@Param("from") String from, @Param("to") String to);

}
