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

	/*
	 * It's automated when a transaction made on any wallet
	 * 
	 * @PostMapping("/transactions/{uuid}") public ResponseEntity<Transaction>
	 * addTransactionHandler(@RequestBody Transaction transaction,
	 * 
	 * @PathVariable("uuid") String uuid) { Transaction savedTransaction =
	 * transactionService.addTransaction(transaction, uuid); return new
	 * ResponseEntity<Transaction>(savedTransaction, HttpStatus.CREATED); }
	 */

	@GetMapping("/transactions/{uuid}")
	public ResponseEntity<List<Transaction>> viewAllTransactionsHandler(@PathVariable("uuid") String uuid) {
		List<Transaction> transactions = transactionService.viewAllTransactions(uuid);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/transactions/{uuid}/bydate")
	public ResponseEntity<List<Transaction>> viewTransactionsByDateHandler(@RequestParam("from") String from,
			@RequestParam("to") String to, @PathVariable("uuid") String uuid) {
		List<Transaction> transactions = transactionService.viewTransactionsByDate(LocalDate.parse(from),
				LocalDate.parse(to), uuid);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping("/transactions/{uuid}/{type}")
	public ResponseEntity<List<Transaction>> viewTransactionsByTypeHandler(@PathVariable("type") String type,
			@PathVariable("uuid") String uuid) {
		List<Transaction> transactions = transactionService.viewAllTransactionsByType(type, uuid);
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}
}
