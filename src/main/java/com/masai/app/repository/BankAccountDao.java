package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.BankAccount;
@Repository
public interface BankAccountDao extends JpaRepository<BankAccount,Integer> {
	
}
