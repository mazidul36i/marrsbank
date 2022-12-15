package com.masai.app.service;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.model.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer)throws CustomerException ;
	public Customer updateCustomer(Customer customer, String Key) throws CustomerException ;
}
