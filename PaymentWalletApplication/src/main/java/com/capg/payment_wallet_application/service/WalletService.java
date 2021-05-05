package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.util.List;


import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.AccountId;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.dto.TransactionDTO;

public interface WalletService {
	public CustomerDTO createAccount(Customer customer);

	public CustomerDTO showBalance(String mobileno);

	public TransactionDTO fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);

	public TransactionDTO depositAmount(int walletId, BigDecimal amount);

	public TransactionDTO withdrawAmount(int walletId, BigDecimal amount);

	public List<CustomerDTO> getList();

	public CustomerDTO updateAccount(Customer customer);

	public TransactionDTO addMoney(BankAccount account, int walletId, double amount);
}
