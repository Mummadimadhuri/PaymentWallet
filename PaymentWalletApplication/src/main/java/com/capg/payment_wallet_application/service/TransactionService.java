/*
 * Implemented Service Name : Transaction Service
 * Author                   : T.Deepan Chakravarthy
 * Implementation Start Date: 2021-04-05
 * implementation End Date  : 2021-04-06
 * Used Annotation          : @Service,@Autowired,@Override
 * Validation               : Validation are done at Required Place
 * */
package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.repo.ITransactionRepository;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.TransactionUtils;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionRepository transactionRepo;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WalletRepo walletRepo;
	
	//Service for addtransaction() method is written in which balance of wallet is get updated after each transaction.
	@Override
	public TransactionDTO addTransaction(Transaction tran) {
		logger.info("addTransaction() is get intiated()");
		BigDecimal currentBalance = tran.getWallet().getBalance();
		BigDecimal amount = (BigDecimal.valueOf(tran.getAmount()));
		Transaction transaction = null;
		if ((amount.compareTo(currentBalance) <= 0) && (tran.getTransactionType().equals("SEND"))) {
			currentBalance = currentBalance.subtract(amount);
			tran.getWallet().setBalance(currentBalance);
		    transaction = transactionRepo.save(tran);
		} else if (tran.getTransactionType().equals("RECEIVE")) {
			currentBalance = currentBalance.add(amount);
			tran.getWallet().setBalance(currentBalance);
		     transaction = transactionRepo.save(tran);
		} else {
			throw new InsufficientBalanceException("Balance of wallet is not Sufficient to do Transaction");
		}
		logger.info("addTransaction() is get executed()");
		return TransactionUtils.convertToTransactionDto(transaction);
	}

	//Service to viewAlltransactions() of the given wallet is written here,It will display each and every fundtransfer done by specific wallet.  
	@Override
	public List<TransactionDTO> viewAllTransactions(int walletId) {
		Customer wallet = walletRepo.findByWalletId(walletId);
		if (wallet != null) {
			logger.info("viewAlltransactions() is get intiated");
			List<Transaction> list = transactionRepo.viewAllTransactions(walletId);
			logger.info("viewAllTransaction() is get executed");
			return TransactionUtils.convertToTransactionDtoList(list);
		} else {
			throw new WalletNotFoundException("Wallet is not found");
		}
	}

	//Service to viewAlltransactions() of the given transaction type is written here.
	@Override
	public List<TransactionDTO> viewAllTransactions(String type) {
		logger.info("viewAlltransactions() is get intiated");
		List<Transaction> list = null;
		if (transactionTypeValidation(type)) {
			list = transactionRepo.viewAllTransactions(type);
		} else {
			throw new InvalidInputException("Transaction types are only either SEND or RECEIVE");
		}
		logger.info("viewAllTransaction() is get executed");
		return TransactionUtils.convertToTransactionDtoList(list);
	}
    
	//Service to viewTransactionByDate() ,which is used to display transaction between the dates.
	@Override
	public List<TransactionDTO> viewTransactionsByDate(@DateTimeFormat(iso = ISO.DATE) LocalDate from,
			@DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		if (from.isBefore(to)) {
			logger.info("viewTransactionByDate() is get intiated");
			List<Transaction> list = transactionRepo.viewTransactionsByDate(from, to);
			logger.info("viewTransactionByDate() is get executed");
			return TransactionUtils.convertToTransactionDtoList(list);
		} else {
			throw new InvalidInputException("From date must be before than to date");
		}
	}

	//Validation to provide transaction type as "SEND" and "RECIEVE".
	private static boolean transactionTypeValidation(String type) {
		boolean flag = false;
		if (type.equals("SEND") || type.equals("RECEIVE")) {
			flag = true;
		}
		return flag;
	}

}
