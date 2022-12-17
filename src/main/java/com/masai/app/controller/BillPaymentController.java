package com.masai.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.BillPaymentException;
import com.masai.app.model.BillPayment;
import com.masai.app.service.BillPaymentService;

@RestController
public class BillPaymentController {

	@Autowired
	private BillPaymentService billPaymentService;

	@PostMapping("/billpayments/{uuid}")
	public ResponseEntity<BillPayment> addBillpaymentHandler(@RequestBody BillPayment billPayment,
			@PathVariable("uuid") String uuid) {
		BillPayment savedBillPayment = billPaymentService.addBillPayment(billPayment, uuid);
		return new ResponseEntity<BillPayment>(savedBillPayment, HttpStatus.CREATED);
	}

	@GetMapping("/billpayments/{uuid}/{billId}")
	public ResponseEntity<BillPayment> getBillPaymentByBillIdHandler(@PathVariable("billId") Integer billId,
			@PathVariable("uuid") String uuid) throws BillPaymentException {

		BillPayment bill = billPaymentService.viewBillPayment(uuid, billId);
		return new ResponseEntity<BillPayment>(bill, HttpStatus.OK);
	}

	@GetMapping("/billpayments/{uuid}")
	public ResponseEntity<List<BillPayment>> getBillpaymentsHandler(@PathVariable("uuid") String uuid) {
		List<BillPayment> bills = billPaymentService.viewAllBillPayments(uuid);
		return new ResponseEntity<List<BillPayment>>(bills, HttpStatus.OK);
	}

}