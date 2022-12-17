package com.masai.app.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.exceptions.InsufficientBalanceException;
import com.masai.app.exceptions.WalletException;
import com.masai.app.model.Customer;
import com.masai.app.model.CustomerDTO;
import com.masai.app.model.Transaction;
import com.masai.app.repository.CustomerDao;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;

	@Override
	public CustomerDTO showBalance(String uuid) throws CustomerException {

		Customer customer = customerService.getCustomerByUuid(uuid);

		// Create a new Customer data transfer object
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName(customer.getName());
		customerDTO.setMobileNumber(customer.getMobileNumber());
		customerDTO.setBalance(customer.getWallet().getBalance());

		// Return the object
		return customerDTO;
	}

	@Override
	public String fundTransafer(String uuid, String targetMobileNumber, Double amount)
			throws WalletException, InsufficientBalanceException {

		// Fetching customers details
		Customer customer = customerService.getCustomerByUuid(uuid);
		Customer customer2 = customerDao.findByMobileNumber(targetMobileNumber);

		// Validate customers
		if (customer2 == null)
			new CustomerException("Customer doesn't exists with mobile number: " + targetMobileNumber);

		// Check balance availability
		if (customer.getWallet().getBalance() < amount)
			throw new InsufficientBalanceException("Insufficient balance!");

		// Transfer amount
		customer.getWallet().setBalance(customer.getWallet().getBalance() - amount);
		customer2.getWallet().setBalance(customer2.getWallet().getBalance() + amount);

		customerDao.save(customer);
		customerDao.save(customer2);

		// Generate transaction record
		Transaction transaction = new Transaction();
		transaction.setTransactionDate(LocalDate.now());
		transaction.setAmount(amount);
		transaction.setDescription("Sent to " + targetMobileNumber);
		transaction.setTransactionType("Mobile transfar");

		// Save the transaction record
		transactionService.addTransaction(transaction, customer.getWallet().getWalletId());

		// Return success message
		return "Transaction successfull!";
	}

	@Override
	public CustomerDTO depositAmount(String uuid, Double amount) throws WalletException {

		// Fetching wallet details
		Customer customer = customerService.getCustomerByUuid(uuid);

		// Validate customers
		if (customer == null)
			new CustomerException("Customer doesn't exists with mobile number: " + customer.getMobileNumber());

		// Deposit amount
		customer.getWallet().setBalance(customer.getWallet().getBalance() + amount);
		Customer savedCustomer = customerDao.save(customer);

		// Create a new Customer data transfer object
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName(savedCustomer.getName());
		customerDTO.setMobileNumber(customer.getMobileNumber());
		customerDTO.setBalance(savedCustomer.getWallet().getBalance());

		// Return the DTO
		return customerDTO;
	}

}
