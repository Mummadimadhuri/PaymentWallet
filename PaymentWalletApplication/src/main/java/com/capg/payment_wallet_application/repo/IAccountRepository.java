package com.capg.payment_wallet_application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capg.payment_wallet_application.beans.AccountId;
import com.capg.payment_wallet_application.beans.BankAccount;
/**
 * Author: Bhavish Reddy
 * Version: 1.0
 * Date: 03-04-2021
 * Description: This is Account Repository Interface
 */
public interface IAccountRepository extends JpaRepository<BankAccount, AccountId> {

	@Query("select bacc from BankAccount bacc where bacc.wallet.walletId=:walletId")
	public List<BankAccount> findByWalletId(@Param("walletId") int walletId);
	
	
}
