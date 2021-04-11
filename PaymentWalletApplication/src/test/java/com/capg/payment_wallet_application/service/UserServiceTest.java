package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletServiceImpl walletService;

	@Test
	void testValidateLogin() {
		CustomerDTO customer = walletService.createAccount("RishiKiran","9555532631",new BigDecimal(1000),"Rishi@2000");
		CustomerDTO customer1 = userService.validateLogin(customer.getMobileNo(),"Rishi@2000");
		assertNotNull(customer);
		assertEquals(customer.getName(),customer1.getName());
	    String mobileNo="8887685851";
	    String password="satheesh2000";
	    assertThrows(InvalidInputException.class,()->userService.validateLogin(mobileNo,password));
	}

}
