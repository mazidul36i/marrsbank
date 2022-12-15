package com.masai.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.Customer;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.SessionDao;

@Service
public class CustomerServiceIMPL implements CustomerService {
	@Autowired
	private CustomerDao cDao;
	@Autowired
	private SessionDao sDao;

	@Override
	public Customer createCustomer(Customer c1) throws CustomerException {
		// TODO Auto-generated method stub
		Customer exsit = cDao.findByMobileNumber(c1.getMobileNumber());
		if (exsit != null)
			throw new CustomerException("Customer Already Registerd With That Mobile NUmber !");

		return cDao.save(c1);
	}

	@Override
	public Customer updateCustomer(Customer customer, String Key) throws CustomerException {
		// TODO Auto-generated method stub
		CurrentUserSession cu = sDao.findByUuid(Key);
		if (cu == null) {
			throw new CustomerException("Please Provide Valid Key to Upadte Customer Profile !");
		}
		if (customer.getCustomerId() == cu.getUserId()) {
			return cDao.save(customer);
		} else {
			throw new CustomerException("Invalid Customer Details Please Login First !");
		}

	}

}
