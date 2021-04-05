package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;

public interface IAccountService {

	public WalletDTO addAccount(BankAccount bacc);

	public WalletDTO removeAccount(BankAccount bacc);

	public List<BankAccountDTO> viewAllAccounts(int walletId);
	
  WalletDTO viewAccount(int accountNo, String ifscCode);
	
}
