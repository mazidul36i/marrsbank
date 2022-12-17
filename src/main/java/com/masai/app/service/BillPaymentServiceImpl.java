package com.masai.app.service;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.BillPaymentException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.BillPayment;
import com.masai.app.model.Wallet;
import com.masai.app.repository.BillPaymentDao;
import com.masai.app.repository.WalletDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private BillPaymentDao billDao;

	@Override
	public BillPayment addBillPayment(BillPayment payment, Integer WalletId)
			throws WalletException, BillPaymentException {
			
		
		Wallet wallet = walletDao.findById(WalletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't not found!"));
	
		long wall = (long) (wallet.getBalance() - payment.getAmount());
			
			if(wall > 0) {
				wallet.setBalance(wall);
				payment.setWallet(wallet);

				return billDao.save(payment);
			}
			else {
			throw  new WalletException("Unsufficient balance Please Wallet update");
			}	
	}

	@Override
	public BillPayment viewBillPayment(Integer billId) throws BillPaymentException {

			BillPayment bill = billDao.findByBillId(billId);

		if (bill != null) {

			return bill;
		} else
			throw new BillPaymentException("Bill Details not Found");
	}
	
	@Override
	public List<BillPayment> viewAllBillPayments(Integer walletId) throws BillPaymentException, WalletException {
		
		Wallet wallet = walletDao.findById(walletId)
				.orElseThrow(() -> new WalletException("Wallet doesn't exists with the wallet Id: " + walletId));
		
		if (wallet.getBillPayments() == null || wallet.getBillPayments().size() == 0)
			throw new BillPaymentException("No bill made on this wallet!");
		
			return wallet.getBillPayments();
	}

}
