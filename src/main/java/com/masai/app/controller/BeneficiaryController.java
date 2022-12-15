package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.Beneficiary;
import com.masai.app.service.BeneficiaryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
	
	
	@Autowired
	BeneficiaryService beneficiaryService;
	
	
	@PostMapping("/beneficiaries")
	public ResponseEntity<Beneficiary> addBeneneficiaryMapping(@RequestBody Beneficiary beneficiary) throws BeneficiaryException{

		return new ResponseEntity<Beneficiary>(beneficiaryService.addBeneficiary(beneficiary),HttpStatus.ACCEPTED);

	}

}
