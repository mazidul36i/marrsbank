package com.masai.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.LoginException;
import com.masai.app.model.LoginDTO;
import com.masai.app.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService cuLogin;

	@PostMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestBody LoginDTO dto) throws LoginException {
		return new ResponseEntity<String>(cuLogin.LoginIntoAccount(dto), HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return new ResponseEntity<String>(cuLogin.LogoutFromAccount(key), HttpStatus.OK);
	}
}
