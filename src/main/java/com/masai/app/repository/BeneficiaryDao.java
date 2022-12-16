package com.masai.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.masai.app.model.Beneficiary;

@Repository
public interface BeneficiaryDao extends JpaRepository<Beneficiary, String> {
	
	public Beneficiary findByMobileNumber(String mobileNumber);

}
