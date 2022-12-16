package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.BillPaymentException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BillPayment;

public interface BillPaymentService {

	public BillPayment addBillPayment(BillPayment payment, Integer WalletId)
			throws WalletException, BillPaymentException;

	public BillPayment viewBillPayment(Integer billId) throws BillPaymentException;

	public List<BillPayment> viewAllBillPayments(Integer walletId) throws BillPaymentException, WalletException;

}
