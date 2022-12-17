package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BillPaymentException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BillPayment;

public interface BillPaymentService {

	public BillPayment addBillPayment(BillPayment payment, String uuid)
			throws WalletException, BillPaymentException;

	public BillPayment viewBillPayment(String uuid, Integer billId) throws BillPaymentException;

	public List<BillPayment> viewAllBillPayments(String uuid) throws BillPaymentException, WalletException;

}
