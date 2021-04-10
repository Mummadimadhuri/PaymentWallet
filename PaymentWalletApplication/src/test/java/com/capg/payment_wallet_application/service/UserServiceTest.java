package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	void testValidateLogin() {
		String mobileNo ="9344479110";
		String password = "Rohith@2000";
		String password1 = "Rohith2000";
		String name ="Rohith";
		
		CustomerDTO customer = userService.validateLogin(mobileNo,password);
		assertEquals(name,customer.getName());
		assertNotNull(customer);
		
		assertThrows(InvalidInputException.class,()->userService.validateLogin(mobileNo,password1));
		
	}

}
