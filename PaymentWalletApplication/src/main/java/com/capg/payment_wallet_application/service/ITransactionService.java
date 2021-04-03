package com.capg.payment_wallet_application.service;

import java.time.LocalDate;
import java.util.List;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;

public interface ITransactionService {

	public TransactionDTO addTransaction (Transaction tran);
	public TransactionDTO viewAllTransactions (Wallet wallet);
	public List<TransactionDTO> viewTransactionsByDate(LocalDate from,LocalDate to);
	public TransactionDTO viewAllTransactions(String type);
}
