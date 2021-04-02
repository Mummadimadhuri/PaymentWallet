package com.capg.payment_wallet_application.repo;

import com.capg.payment_wallet_application.beans.Customer;

public interface IUserRepo {

	public Customer validateLogin(String mobileNumber,String password);
}
