package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.dto.TransactionDTO;

public class TransactionUtils {
	public static List<TransactionDTO> convertToTransactionDtoList(List<Transaction> list) {
		List<TransactionDTO> dtolist = new ArrayList<TransactionDTO>();
		for (Transaction Transaction : list)
			dtolist.add(convertToTransactionDto(Transaction));
		return dtolist;
	}
	public static Transaction convertToTransaction(TransactionDTO dto) {
		Transaction transaction = new Transaction();
	        transaction.setTransactionId(dto.getTransactionId());
			transaction.setAmount(dto.getAmount());
			transaction.setTransactionType(dto.getTransactionType());;
			transaction.setTransactionDate(dto.getTransactionDate());
            transaction.setWallet(dto.getWallet());	
            transaction.setDescription(dto.getDescription());
			return transaction;
		
	}
	public static TransactionDTO convertToTransactionDto(Transaction transaction)
	{
		TransactionDTO transactiondto = new TransactionDTO();
		transactiondto.setTransactionId(transaction.getTransactionId());
		transactiondto.setAmount(transaction.getAmount());
		transactiondto.setTransactionType(transaction.getTransactionType());
		transactiondto.setTransactionDate(transaction.getTransactionDate());
		transactiondto.setWallet(transaction.getWallet());
		transactiondto.setDescription(transaction.getDescription());
		return transactiondto;
	}
}