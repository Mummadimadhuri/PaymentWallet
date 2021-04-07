package com.capg.payment_wallet_application.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.service.WalletService;

@RestController
@RequestMapping("/api/pwa/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;
    	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/createAccount/{name}/{mobileno}/{amount}/{password}")
	public CustomerDTO createAccount(@PathVariable String name, @PathVariable String mobileno,
			@PathVariable BigDecimal amount,@PathVariable String password) {
		logger.info("Customer profile is created sucessfully");
		return walletService.createAccount(name, mobileno, amount,password);
	}

	@GetMapping("/showBalance/{mobileno}")
	public CustomerDTO showBalance(@PathVariable String mobileno) {
		logger.info("Balance of the given mobile number is displayed sucessfully");
		return walletService.showBalance(mobileno);
	}

	@PutMapping("/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public CustomerDTO fundTransfer(@PathVariable String sourceMobileNo, @PathVariable String targetMobileNo,
			@PathVariable BigDecimal amount) {
		logger.info("Fund transfer and transaction between source and target held sucessfully");
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	}

	@PutMapping("/depositAmount/{mobileNo}/{amount}")
	public CustomerDTO depositAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		logger.info("The amount is deposited in the wallet of given mobilenumber");
		return walletService.depositAmount(mobileNo, amount);
	}

	@PutMapping("/withdrawAmount/{mobileNo}/{amount}")
	public CustomerDTO withdrawAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		logger.info("The amount is withdraw from the wallet of given mobilenumber");
		return walletService.withdrawAmount(mobileNo, amount);
	}

	@GetMapping("/getList")
	public List<CustomerDTO> getList() {
		logger.info("The List of customer is displayed sucessfully");
		return walletService.getList();
	}

	@PutMapping("/updateAccount")
	public CustomerDTO updateAccount(@RequestBody Customer customer) {
		logger.info("The customer profile is updated sucessfully");
		return walletService.updateAccount(customer);
	}

	@PutMapping("/addMoney/{amount}")
	public CustomerDTO addMoney(@RequestBody Wallet wallet, @PathVariable double amount) {
		logger.info("The money is added into wallet sucessfully");
		return walletService.addMoney(wallet, amount);
	}

}