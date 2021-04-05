package com.capg.payment_wallet_application.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/validate/{mobileNo}/{password}")
	public CustomerDTO validateLogin(@PathVariable String mobileNo,@PathVariable String password) {
		LOGGER.info("Login Done sucessfully");
		return userService.validateLogin(mobileNo, password); 
	}
}
