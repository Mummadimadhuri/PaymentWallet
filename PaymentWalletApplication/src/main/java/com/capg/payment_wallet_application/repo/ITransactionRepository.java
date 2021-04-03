package com.capg.payment_wallet_application.repo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{

	final Logger LOGGER = LoggerFactory.getLogger(ITransactionRepository.class);
	
	@Query("select t from Transaction t where t.wallet = :wallet")
	public List<Transaction> viewAllTransactions (@Param(value = "wallet") Wallet wallet);
	
	@Query("SELECT t FROM Transaction t WHERE t.transactionDate BETWEEN :from AND :to")
	public List<Transaction> viewTransactionsByDate(@Param(value = "from") @DateTimeFormat(iso = ISO.DATE) LocalDate from,@Param(value = "to") @DateTimeFormat(iso = ISO.DATE) LocalDate to);
	
	@Query("select t from Transaction t where t.transactionType = :type")
	public List<Transaction> viewAllTransactions(@Param(value = "type") String type);
}
