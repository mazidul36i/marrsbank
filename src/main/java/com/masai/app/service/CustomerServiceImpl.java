package com.masai.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.Customer;
import com.masai.app.model.Wallet;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.SessionDao;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao cDao;
	@Autowired
	private SessionDao sDao;

	@Override
	public Customer createCustomer(Customer c1) throws CustomerException {
		Customer exsit = cDao.findByMobileNumber(c1.getMobileNumber());
		if (exsit != null)
			throw new CustomerException("Customer already registerd with that mobile number !");
		
		Wallet wallet = new Wallet();
		wallet.setBalance(0.0);
		c1.setWallet(wallet);
		return cDao.save(c1);
	}

	@Override
	public Customer updateCustomer(Customer customer, String Key) throws CustomerException {
		CurrentUserSession cu = sDao.findByUuid(Key);
		if (cu == null) {
			throw new CustomerException("Please provide valid key to upadte customer profile !");
		}

		Customer customer2 = cDao.findById(cu.getUserId())
				.orElseThrow(() -> new CustomerException("User doesn't found!"));

		// Update account in local by validating
		if (customer.getName() != null)
			customer2.setName(customer.getName());
		if (customer.getMobileNumber() != null)
			customer2.setMobileNumber(customer.getMobileNumber());
		if (customer.getPassword() != null)
			customer2.setPassword(customer.getPassword());

		Customer savedCustomer = cDao.save(customer2);
		savedCustomer.setPassword(null);
		return savedCustomer;
	}

	@Override
	public Customer getCustomerByUuid(String uuid) throws CustomerException {
		CurrentUserSession useseSession = sDao.findById(uuid)
				.orElseThrow(() -> new CustomerException("Invalid token!"));
		Customer customer = cDao.findById(useseSession.getUserId())
				.orElseThrow(() -> new CustomerException("Access denied!"));
		return customer;
	}
	
	@Override
	public List<Customer> getCustomerList() throws CustomerException {
		List<Customer> customers = cDao.findAll();
		if (customers == null || customers.size() == 0)
			throw new CustomerException("No customer(s) fount to load!");
		for (Customer customer : customers) {
			customer.setPassword(null);
		}
		return customers;
	}

}
