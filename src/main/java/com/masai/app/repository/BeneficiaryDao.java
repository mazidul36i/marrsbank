package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.Beneficiary;



@Repository
public interface BeneficiaryDao extends JpaRepository<Beneficiary, String> {
	
	
	

}
