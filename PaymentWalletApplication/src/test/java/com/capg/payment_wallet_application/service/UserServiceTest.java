package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.dto.CustomerDTO;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	void testValidateLogin() {
		CustomerDTO customer = userService.validateLogin("9344479110", "Rohith@2000");
		assertNotNull(customer);
	}

}
