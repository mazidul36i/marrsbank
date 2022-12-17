package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.model.CustomerDTO;
import com.masai.app.service.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/{uuid}")
	public ResponseEntity<CustomerDTO> showBalanceHandler(@PathVariable("uuid") String uuid) {
		CustomerDTO customerDTO = walletService.showBalance(uuid);
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
	}

	@PostMapping("/{uuid}")
	public ResponseEntity<String> fundTransferHandler(@PathVariable("uuid") String uuid,
			@RequestParam("targetMobileNumber") String targetMobileNumber, @RequestParam("amount") Double amount) {
		String msg = walletService.fundTransafer(uuid, targetMobileNumber, amount);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping("/deposit/{uuid}")
	public ResponseEntity<CustomerDTO> depositeAmountHandler(@PathVariable("uuid") String uuid,
			@RequestParam("amount") Double amount) {
		CustomerDTO customerDTO = walletService.depositAmount(uuid, amount);
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.ACCEPTED);
	}

}
