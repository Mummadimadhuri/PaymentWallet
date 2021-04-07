package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.capg.payment_wallet_application.dto.CustomerDTO;

class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	void testValidateLogin() {
		CustomerDTO customer = userService.validateLogin("9876543210", "@Aa1@Aa1");
		assertNotNull(customer);
	}

}
