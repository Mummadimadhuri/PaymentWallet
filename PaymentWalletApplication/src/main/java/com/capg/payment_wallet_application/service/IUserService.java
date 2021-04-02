package com.capg.payment_wallet_application.service;

import com.capg.payment_wallet_application.beans.Customer;

public interface IUserService {

	public Customer validateLogin(String mobileNumber,String password);
}
