package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
/**
 * Author: Bhavish Reddy
 * Version: 1.0
 * Date: 03-04-2021
 * Description: This is Account Service Interface
 */
public interface IAccountService {

	public WalletDTO addAccount(BankAccount bankAccount);

	public WalletDTO removeAccount(BankAccount bankAccount);

	public List<BankAccountDTO> viewAllAccounts(int walletId);
	
    WalletDTO viewAccount(int accountNo, String ifscCode);
	
}
