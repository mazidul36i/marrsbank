package com.masai.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.app.model.Wallet;

public interface WalletDao  extends JpaRepository<Wallet, Integer>{

}
