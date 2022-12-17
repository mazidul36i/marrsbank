package com.masai.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.app.exceptions.CustomerException;
import com.masai.app.model.Customer;
import com.masai.app.service.CustomerService;
import com.masai.app.service.WalletService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@GetMapping
	public String welcome() {
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>MARRS Bank</title>\r\n"
				+ "    <link rel=\"shortcut icon\" href=\"https://drive.google.com/uc?export=download&id=11RC-VU8DuDYIXqBCCReOHXeKiW3sFlVa\"\r\n"
				+ "        type=\"image/x-icon\">\r\n"
				+ "\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            background-color: #06132c;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        * {\r\n"
				+ "            color: white;\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "\r\n"
				+ "        a {\r\n"
				+ "            font-size: 18px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <h1 style=\"color: aqua;\">MARRS Bank</h1>\r\n"
				+ "        <h2>Login or create new Account to continue with MARRS Bank!</h2>\r\n"
				+ "        <a style=\"color: blue;\" href=\"http://localhost:8888/swagger-ui\">Swagger UI</a>\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "</body>\r\n"
				+ "\r\n"
				+ "</html>";
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomerHandler(@RequestBody Customer customer) throws CustomerException {
		Customer savedCustomer = cs.createCustomer(customer);
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer,
			@RequestParam("token") String key) throws CustomerException {
		Customer updatedCustomer = cs.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomerListHandler() {
		List<Customer> customers = cs.getCustomerList();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

}
