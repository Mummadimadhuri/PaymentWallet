package com.capg.payment_wallet_application.service;

import java.time.LocalDate;
import java.util.List;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;

public interface ITransactionService {

	public Transaction addTransaction (Transaction tran);
	public Transaction viewAllTransactions (Wallet wallet);
	public List<Transaction> viewTransactionsByDate(LocalDate from,LocalDate to);
	public Transaction viewAllTransactions(String type);
}
