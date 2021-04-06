package com.capg.payment_wallet_application.service;

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

	@Override
	public CustomerDTO validateLogin(String mobileNumber, String password) {
		Customer customer = userRepo.validateLogin(mobileNumber, Integer.valueOf(password.hashCode()).toString()/*password*/);

		if(customer==null) {
			throw new InvalidInputException("Wrong Credentials");
		} else {
			return CustomerUtils.convertToCustomerDto(customer);
		}
	}

}
