package com.masai.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.app.exceptions.LoginException;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.Customer;
import com.masai.app.model.LoginDTO;
import com.masai.app.repository.CustomerDao;
import com.masai.app.repository.SessionDao;

@Service
public class LoginServiceImpl implements LoginService {

	// Autowire daos'
	@Autowired
	private CustomerDao cDao;

	@Autowired
	private SessionDao sDao;

	@Override
	public CurrentUserSession LoginIntoAccount(LoginDTO dto) throws LoginException {

		// Check if the user exists or not
		Customer exist = cDao.findByMobileNumber(dto.getMobileNumber());
		if (exist == null) {
			throw new LoginException("Please enter a valid mobile number!");
		}

		// Validate password
		if (exist.getPassword().equals(dto.getPassword())) {
			String uuid = UUID.randomUUID().toString();
			CurrentUserSession cus = new CurrentUserSession(uuid, exist.getCustomerId(), LocalDateTime.now());
			sDao.save(cus);
			return cus;
		} else {
			// Throw exception on invalid password
			throw new LoginException("Please enter a valid password!");
		}
	}

	@Override
	public String LogoutFromAccount(String key) throws LoginException {
		// Validate token
		CurrentUserSession cus = sDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Invalid operation!");
		}

		// Log out
		sDao.delete(cus);
		return "Logged Out!";
	}

	@Override
	public String LogoutFromAllAccounts(String key) throws LoginException {
		// Validate current user token
		CurrentUserSession cus = sDao.findByUuid(key);
		if (cus == null) {
			throw new LoginException("Access denied!");
		}

		// Get list of current sessions running on the account
		List<CurrentUserSession> sessions = sDao.findByUserId(cus.getUserId());

		// Remove all except the current user
		for (CurrentUserSession session : sessions) {
			if (session.getUuid() != cus.getUuid())
				sDao.delete(session);
		}

		// Return success message
		return "Logged out from all devices!";
	}

}
