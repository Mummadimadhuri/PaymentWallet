package com.capg.payment_wallet_application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;

public interface IAccountRepository extends JpaRepository<Wallet, Integer> {

//	public Wallet addAccount(BankAccount bacc);
//	public Wallet removeAccount(BankAccount bacc);
	public Wallet findByBankAccount(BankAccount bacc);
	public List<Wallet> findByWallet(Wallet wallet);
	
	
}
