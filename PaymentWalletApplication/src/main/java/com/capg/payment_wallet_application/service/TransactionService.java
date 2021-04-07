package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;
import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.ITransactionRepository;
import com.capg.payment_wallet_application.util.TransactionUtils;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	ITransactionRepository transactionRepo;

	@Override
	public TransactionDTO addTransaction(Transaction tran) {
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
		return TransactionUtils.convertToTransactionDto(transaction);
	}

	@Override
	public List<TransactionDTO> viewAllTransactions(int walletId) {

		List<Transaction> list = transactionRepo.viewAllTransactions(walletId);
		return TransactionUtils.convertToTransactionDtoList(list);
	}

	@Override
	public List<TransactionDTO> viewAllTransactions(String type) {
		List<Transaction> list = null;
		if (transactionTypeValidation(type)) {
			list = transactionRepo.viewAllTransactions(type);
		} else {
			throw new InvalidInputException("Transaction types are only either SEND or RECEIVE");
		}
		return TransactionUtils.convertToTransactionDtoList(list);
	}

	@Override
	public List<TransactionDTO> viewTransactionsByDate(@DateTimeFormat(iso = ISO.DATE) LocalDate from,
			@DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		List<Transaction> list = transactionRepo.viewTransactionsByDate(from, to);
		return TransactionUtils.convertToTransactionDtoList(list);
	}

	private static boolean transactionTypeValidation(String type) {
		boolean flag = false;
		if (type.equals("SEND") || type.equals("RECEIVE")) {
			flag = true;
		}
		return flag;
	}

}
