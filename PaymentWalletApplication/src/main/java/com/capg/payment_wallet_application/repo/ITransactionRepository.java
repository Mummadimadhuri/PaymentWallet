package com.capg.payment_wallet_application.repo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{

	final Logger LOGGER = LoggerFactory.getLogger(ITransactionRepository.class);
	
	
	public TransactionDTO viewAllTransactions (Wallet wallet);
	
	@Query("SELECT t FROM Transaction t WHERE t.transactiondate BETWEEN :from AND :to")
	public List<TransactionDTO> viewTransactionsByDate(LocalDate from,LocalDate to);
	
	
	public TransactionDTO viewAllTransactions(String type);
}
