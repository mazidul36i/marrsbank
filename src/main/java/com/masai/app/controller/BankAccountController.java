package com.masai.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;
import com.masai.app.service.BankAccountService;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@PostMapping("/{uuid}")
	public ResponseEntity<BankAccount> addBankAccount(@RequestBody BankAccount bank,
			@PathVariable("uuid") String uuid) {

		BankAccount account = bankAccountService.addAccount(bank, uuid);
		return new ResponseEntity<BankAccount>(account, HttpStatus.CREATED);
	}

	@DeleteMapping("/{uuid}/{accountNumber}")
	public ResponseEntity<Wallet> deleteByBankAccount(@PathVariable("accountNumber") String accNo,
			@PathVariable("uuid") String uuid) {

		Wallet wallet = bankAccountService.deleteAccount(accNo, uuid);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<List<BankAccount>> viewAllBankAccount(@PathVariable("uuid") String uuid) {

		List<BankAccount> accounts = bankAccountService.viewBankAccount(uuid);
		return new ResponseEntity<List<BankAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping("/{uuid}/{accNo}")
	public ResponseEntity<BankAccount> viewBankAccountByAccountNo(@PathVariable("accNo") String accountNo,
			@PathVariable("uuid") String uuid) {

		BankAccount account = bankAccountService.viewBankAccountByAccounNo(accountNo, uuid);
		return new ResponseEntity<BankAccount>(account, HttpStatus.OK);
	}

}
