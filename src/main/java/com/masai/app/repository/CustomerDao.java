package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Customer findByMobileNumber(String Mob);

//	@Query("SELECT new com.masai.app.model.CustomerDTO(c.name, c.mobileNumber, w.balance) FROM Customer c INNER JOIN Wallet w WHERE c.wallet_wallet_id = w.wallet_id")
//	public List<CustomerDTO> getAllCustomerDTO();
}
