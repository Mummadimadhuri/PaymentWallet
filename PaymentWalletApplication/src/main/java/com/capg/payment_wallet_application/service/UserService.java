package com.capg.payment_wallet_application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.IUserRepo;
import com.capg.payment_wallet_application.util.CustomerUtils;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepo userRepo;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public CustomerDTO validateLogin(String mobileNumber, String password) {
		logger.info("validateLogin() is get intiated");
		password = Integer.valueOf(password.hashCode()).toString();
		Customer customer = userRepo.validateLogin(mobileNumber, password);
		if (customer == null) {
			throw new InvalidInputException("Wrong Credentials");
		} else {
			logger.info("validateLogin() is get executed");
			return CustomerUtils.convertToCustomerDto(customer);
		}
	}

}
