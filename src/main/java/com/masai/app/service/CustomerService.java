package com.masai.app.service;

import java.util.List;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer, String Key) throws CustomerException;

	public Customer getCustomerByUuid(String uuid) throws CustomerException;

	public List<Customer> getCustomerList() throws CustomerException;
}
