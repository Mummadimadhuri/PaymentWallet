package com.capg.payment_wallet_application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.service.AccountServiceImpl;

@RestController
@RequestMapping("/api/pwa/account")
public class AccountController {

	@Autowired
	private AccountServiceImpl accountService;
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/add")
	public WalletDTO addAccount(@RequestBody BankAccount bacc) {
		logger.info("Account added succesfully");
		return accountService.addAccount(bacc);
	}

	@DeleteMapping("/remove")
	public WalletDTO removeAccount(@RequestBody BankAccount bacc) {
		logger.info("Account deleted");
		return accountService.removeAccount(bacc);
	}


	@GetMapping("/view/{accountNo}/{ifscCode}")
	public WalletDTO viewAccount(@PathVariable int accountNo,@PathVariable String ifscCode) {
		logger.info("wallet for the account fetched");
		return accountService.viewAccount(accountNo,ifscCode);
	}

	@GetMapping("/viewAll/{walletId}")
	public List<BankAccountDTO> viewAllAccounts(@PathVariable int walletId) {
		logger.info("all accounts linked with the wallet are fetched");
		return accountService.viewAllAccounts(walletId);


	}

}
