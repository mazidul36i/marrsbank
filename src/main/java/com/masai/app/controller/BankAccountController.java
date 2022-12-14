package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.model.BankAccount;
import com.masai.app.model.Wallet;
import com.masai.app.service.BankAccountService;


@RestController
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@PostMapping("/bankaccounts/{id}")
	public ResponseEntity<Wallet> addBankAccount(@RequestBody BankAccount bank ,@PathVariable("id") Integer walletId ){
		
		Wallet wallet=bankAccountService.addAccount(bank,walletId);
		return new ResponseEntity<Wallet>(wallet,HttpStatus.CREATED);
		
	}  
	
	
	
	@DeleteMapping("/bankaccounts")
	public ResponseEntity<Wallet> deleteByBankAccount(@RequestBody BankAccount bank  ){
		
		Wallet wallet=bankAccountService.deleteAccount(bank);
		return new ResponseEntity<Wallet>(wallet,HttpStatus.CREATED);
		
	}  
	
	

}
