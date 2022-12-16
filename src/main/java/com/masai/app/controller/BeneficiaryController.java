package com.masai.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.BankAccount;
import com.masai.app.model.Beneficiary;
import com.masai.app.service.BeneficiaryService;





@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	
	@PostMapping("/beneficiaries/{walletId}")
	public ResponseEntity<Beneficiary> addBeneneficiaryMapping(@RequestBody Beneficiary beneficiary ,@PathVariable ("walletId") Integer walletId) throws BeneficiaryException{

		Beneficiary beneficiary2 = beneficiaryService.addBeneficiary(beneficiary, walletId);
		return new ResponseEntity<Beneficiary>(beneficiary2 ,HttpStatus.ACCEPTED);

	}
	
	
	
	@DeleteMapping("/beneficiaries/{walletId}")
	public ResponseEntity<Beneficiary> deleteBeneneficiaryMapping( @RequestParam("mobileNumber") String mobileNumber,@PathVariable ("walletId") Integer walletId) throws BeneficiaryException{
		
		return new ResponseEntity<Beneficiary>(beneficiaryService.deleteBeneficiary(walletId, mobileNumber),HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/beneficiaries/{mobileNumber}")
	public ResponseEntity<Beneficiary> viewBeneficiaryMapping(@PathVariable("mobileNumber")String mobileNumber) throws BeneficiaryException {

		Beneficiary beneficiary = beneficiaryService.viewBeneficiary(mobileNumber);
		
		return new ResponseEntity<Beneficiary>(beneficiary, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/beneficiaries/{customerId}")
	public ResponseEntity<List<Beneficiary>> viewAllBeneficiaryMapping(@PathVariable("customerId")Integer customerId) throws BeneficiaryException {

		List<Beneficiary> beneficiary = beneficiaryService.viewAllBeneficiary(customerId);
		
		return new ResponseEntity<>(beneficiary, HttpStatus.ACCEPTED);

	}
	
	
	
	

}
