package com.capg.payment_wallet_application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;

public interface IAccountRepository extends JpaRepository<BankAccount, Integer> {

//	public Wallet addAccount(BankAccount bacc);
//	public Wallet removeAccount(BankAccount bacc);
	
	public BankAccount findByBankAccount(BankAccount bacc);
	public List<BankAccount> findByWallet(Wallet wallet);
	
	
}
