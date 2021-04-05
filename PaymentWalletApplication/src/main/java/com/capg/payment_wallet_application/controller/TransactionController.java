package com.capg.payment_wallet_application.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.service.TransactionService;



@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping(value = "/add", consumes = "application/json")
	public TransactionDTO addTransaction (@RequestBody Transaction tran)
	{
		return transactionService.addTransaction(tran);
	}
	
	@GetMapping(value = "/get-wallet-transaction/{walletId}/{balance}", produces = "application/json")
	public List<TransactionDTO> viewAllTransactions (Wallet wallet) {
		return transactionService.viewAllTransactions(wallet);
	}
	
	@GetMapping(value = "/get-all-betweendates/{from}/{to}", produces = "application/json")
	public List<TransactionDTO> viewTransactionsByDate(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate from,@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate to)
	{
		return transactionService.viewTransactionsByDate(from,to);
	}
	
	@GetMapping(value = "/get-all-types/{type}", produces = "application/json")
	public List<TransactionDTO> viewAllTransactions(@PathVariable String type)
	{
		return transactionService.viewAllTransactions(type);
	}
}
