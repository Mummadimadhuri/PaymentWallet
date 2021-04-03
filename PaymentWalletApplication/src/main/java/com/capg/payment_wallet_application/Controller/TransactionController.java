package com.capg.payment_wallet_application.Controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(value = "/add", consumes = "application/json")
	public TransactionDTO addTransaction (@RequestBody Transaction tran)
	{
		return transactionService.addTransaction(tran);
	}
	
	@GetMapping(value = "/get-wallet-transaction", produces = "application/json")
	public TransactionDTO viewAllTransactions (Wallet wallet) {
		LOGGER.info("Get By wallet available");
		return transactionService.viewAllTransactions(wallet);
	}
	
	@GetMapping(value = "/get-all-betweendates", produces = "application/json")
	public List<TransactionDTO> viewTransactionsByDate(LocalDate from,LocalDate to)
	{
		return transactionService.viewTransactionsByDate(from,to);
	}
	@GetMapping(value = "/get-all-types", produces = "application/json")
	public TransactionDTO viewAllTransactions(String type)
	{
		return transactionService.viewAllTransactions(type);
	}
}
