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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.BeneficiaryException;
import com.masai.app.model.BeneficiaryDetails;
import com.masai.app.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;

	@PostMapping("/{walletId}")
	public ResponseEntity<BeneficiaryDetails> addBeneneficiaryMapping(@RequestBody BeneficiaryDetails beneficiary,
			@PathVariable("walletId") Integer walletId) throws BeneficiaryException {

		BeneficiaryDetails beneficiary2 = beneficiaryService.addBeneficiary(beneficiary, walletId);
		return new ResponseEntity<BeneficiaryDetails>(beneficiary2, HttpStatus.CREATED);
	}

	@DeleteMapping("/{walletId}")
	public ResponseEntity<BeneficiaryDetails> deleteBeneneficiaryMapping(
			@RequestParam("mobileNumber") String mobileNumber, @PathVariable("walletId") Integer walletId)
			throws BeneficiaryException {

		BeneficiaryDetails beneficiary = beneficiaryService.deleteBeneficiary(walletId, mobileNumber);
		return new ResponseEntity<BeneficiaryDetails>(beneficiary, HttpStatus.ACCEPTED);
	}

	@GetMapping("/bymobile/{mobileNumber}")
	public ResponseEntity<BeneficiaryDetails> viewBeneficiaryMapping(@PathVariable("mobileNumber") String mobileNumber)
			throws BeneficiaryException {

		BeneficiaryDetails beneficiary = beneficiaryService.viewBeneficiary(mobileNumber);
		return new ResponseEntity<BeneficiaryDetails>(beneficiary, HttpStatus.OK);

	}

	@GetMapping("/{walletId}")
	public ResponseEntity<List<BeneficiaryDetails>> viewAllBeneficiaryMapping(
			@PathVariable("walletId") Integer walletId) throws BeneficiaryException {

		List<BeneficiaryDetails> beneficiary = beneficiaryService.viewAllBeneficiary(walletId);
		return new ResponseEntity<>(beneficiary, HttpStatus.OK);
	}

}
