package com.masai.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.BillPaymentException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BillPayment;
import com.masai.app.model.Customer;
import com.masai.app.model.Transaction;
import com.masai.app.repository.BillPaymentDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BillPaymentDao billDao;

	@Autowired
	private TransactionService transactionService;

	@Override
	public BillPayment addBillPayment(BillPayment payment, String uuid) throws WalletException, BillPaymentException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		Double wall = customer.getWallet().getBalance() - payment.getAmount();

		if (wall >= 0) {
			customer.getWallet().setBalance(wall);
			payment.setWallet(customer.getWallet());

			// Generate transaction record
			Transaction transaction = new Transaction();
			transaction.setTransactionDate(LocalDate.now());
			transaction.setAmount(payment.getAmount());
			transaction.setDescription("Bill payment");
			transaction.setTransactionType(payment.getBillType());

			// Save the transaction record
			transactionService.addTransaction(transaction, customer.getWallet().getWalletId());

			return billDao.save(payment);
		} else {
			throw new WalletException("Insufficient balance!");
		}
	}

	@Override
	public BillPayment viewBillPayment(String uuid, Integer billId) throws BillPaymentException {

		Customer customer = customerService.getCustomerByUuid(uuid);
		BillPayment payment = null;

		for (BillPayment payment2 : customer.getWallet().getBillPayments()) {
			if (payment2.getBillId() == billId) {
				payment = payment2;
				break;
			}
		}

		if (payment != null) {
			return payment;
		} else
			throw new BillPaymentException("Bill Details not Found");
	}

	@Override
	public List<BillPayment> viewAllBillPayments(String uuid) throws BillPaymentException, WalletException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		if (customer.getWallet().getBillPayments() == null || customer.getWallet().getBillPayments().size() == 0)
			throw new BillPaymentException("No bill made on this wallet!");

		return customer.getWallet().getBillPayments();
	}

}