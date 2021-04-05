package com.capg.payment_wallet_application.controller;

import java.math.BigDecimal;
import java.util.List;

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
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@PostMapping("/createAccount/{name}/{mobileno}/{amount}")
	public CustomerDTO createAccount(@PathVariable String name, @PathVariable String mobileno,
			@PathVariable BigDecimal amount) {
		return walletService.createAccount(name, mobileno, amount);
	}

	@GetMapping("/showBalance/{mobileno}")
	public CustomerDTO showBalance(@PathVariable String mobileno) {
		return walletService.showBalance(mobileno);
	}

	@PutMapping("/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public CustomerDTO fundTransfer(@PathVariable String sourceMobileNo, @PathVariable String targetMobileNo,
			@PathVariable BigDecimal amount) {
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	}

	@PutMapping("/depositAmount/{mobileNo}/{amount}")
	public CustomerDTO depositAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		return walletService.depositAmount(mobileNo, amount);
	}

	@PutMapping("/withdrawAmount/{mobileNo}/{amount}")
	public CustomerDTO withdrawAmount(@PathVariable String mobileNo, @PathVariable BigDecimal amount) {
		return walletService.withdrawAmount(mobileNo, amount);
	}

	@GetMapping("/getList")
	public List<CustomerDTO> getList() {
		return walletService.getList();
	}

	@PutMapping("/updateAccount")
	public CustomerDTO updateAccount(@RequestBody Customer customer) {
		return walletService.updateAccount(customer);
	}

	@PutMapping("/addMoney/{amount}")
	public CustomerDTO addMoney(@RequestBody Wallet wallet, @PathVariable double amount) {
		return walletService.addMoney(wallet, amount);
	}

}