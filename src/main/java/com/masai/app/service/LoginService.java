package com.masai.app.service;

import com.masai.app.exceptions.LoginException;
import com.masai.app.model.CurrentUserSession;
import com.masai.app.model.LoginDTO;

public interface LoginService {
	
	public CurrentUserSession LoginIntoAccount(LoginDTO dto) throws LoginException;

	public String LogoutFromAccount(String key) throws LoginException;
	
	public String LogoutFromAllAccounts(String key) throws LoginException;
}
