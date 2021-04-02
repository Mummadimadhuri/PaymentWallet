package com.capg.payment_wallet_application.repo;

import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;

public interface IAccountRepository {

	public Wallet addAccount(BankAccount bacc);
	public Wallet removeAccount(BankAccount bacc);
	public Wallet viewAccount(BankAccount bacc);
	public List<Wallet> viewAllAccounts(Wallet wallet);
	
	
}
