package com.masai.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.app.exceptions.LoginException;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.Customer;
import com.masai.app.model.LoginDTO;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.SessionDao;

import net.bytebuddy.utility.RandomString;

public class LoginServiceImpl implements LoginService {
 
	@Autowired
	private CustomerDao cDao;
	
	@Autowired
	private SessionDao sDao;
	
	@Override
	public String LoginIntoAccount(LoginDTO dto) throws LoginException {
		// TODO Auto-generated method stub
		Customer exist=cDao.findByMobileNumber(dto.getMobileNo());
		if(exist==null) {
			throw new LoginException("Please Enter A Valid Mobile Number");
		}
		Optional<CurrentUserSession> validCustomer= sDao.findById(exist.getCustomerId());
		if(validCustomer.isPresent()) {
			throw new LoginException("User Alredy Login With That Mobile Number !");
		}
		if(exist.getPassword().equals(dto.getPassword())) {
			String Key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession(exist.getCustomerId(), Key, LocalDateTime.now());
			sDao.save(cus);
			return cus.toString();
		}else {
			throw new LoginException("Please Enter A Valid Password !");
		}
		
	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException {
		// TODO Auto-generated method stub
		CurrentUserSession cus=sDao.findByUuid(key);
		if(cus==null) {
			throw new LoginException("User Not Login With this Number !");
		}
		sDao.delete(cus);
		return "Logged Out !";
		
	}

}
