package com.capg.payment_wallet_application.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.repo.ITransactionRepository;
import com.capg.payment_wallet_application.util.TransactionUtils;

@Service
public class TransactionService implements ITransactionService {
	
	@Autowired
	ITransactionRepository transactionRepo;
	
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public TransactionDTO addTransaction(Transaction tran) {
		// TODO Auto-generated method stub
		Transaction transaction = transactionRepo.save(tran);
		return TransactionUtils.convertToTransactionDto(transaction) ;
	}

	@Override
	public List<TransactionDTO> viewAllTransactions(Wallet wallet) {
		// TODO Auto-generated method stub
		List<TransactionDTO> list = transactionRepo.viewAllTransactions(wallet);
		return list;
	}

	@Override
	public List<TransactionDTO> viewTransactionsByDate(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		List<TransactionDTO> list = transactionRepo.viewTransactionsByDate(from, to);
		return list;
	}

	@Override
	public List<TransactionDTO> viewAllTransactions(String type) {
		// TODO Auto-generated method stub
		List<TransactionDTO> list =  transactionRepo.viewAllTransactions(type);
		return list;
	}

}
