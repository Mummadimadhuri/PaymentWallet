package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;

public interface IAccountService {

	public Wallet addAccount(BankAccount bacc);
	public Wallet removeAccount(BankAccount bacc);
	public Wallet viewAccount(BankAccount bacc);
	public List<Wallet> viewAllAccounts(Wallet wallet);
	
	
}
