package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.app.model.BeneficiaryDetails;

@Repository
public interface BeneficiaryDao extends JpaRepository<BeneficiaryDetails, String> {

}
