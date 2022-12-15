package com.masai.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.model.Transaction;
import com.masai.app.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transactions/{walletId}")
	public ResponseEntity<Transaction> addTransactionHandler(@RequestBody Transaction transaction,
			@PathVariable("walletId") Integer walletId) {
		Transaction savedTransaction = transactionService.addTransaction(transaction, walletId);
		return new ResponseEntity<Transaction>(savedTransaction, HttpStatus.CREATED);
	}

	@GetMapping("/transactions/{walletId}")
	public ResponseEntity<List<Transaction>> viewAllTransactionsHandler(@PathVariable("walletId") Integer walletId) {
		List<Transaction> transactions = transactionService.viewAllTransactions(walletId);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/transactions/{walletId}/bydate")
	public ResponseEntity<List<Transaction>> viewTransactionsByDateHandler(@RequestParam("from") String from,
			@RequestParam("to") String to, @PathVariable("walletId") Integer walletId) {
		List<Transaction> transactions = transactionService.viewTransactionsByDate(LocalDate.parse(from), LocalDate.parse(to), walletId);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/transactions/{walletId}/{type}")
	public ResponseEntity<List<Transaction>> viewTransactionsByTypeHandler(@PathVariable("type") String type,
			@PathVariable("walletId") Integer walletId) {
		List<Transaction> transactions = transactionService.viewAllTransactionsByType(type, walletId);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}
}
