package com.masai.app.service;

import com.masai.app.exceptions.LoginException;
import com.masai.app.model.LoginDTO;

public interface LoginService  {
public String LoginIntoAccount(LoginDTO dto) throws LoginException;
public String LogoutFromAccount(String key) throws LoginException;
}
