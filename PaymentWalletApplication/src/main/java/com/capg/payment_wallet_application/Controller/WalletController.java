package com.capg.payment_wallet_application.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@PostMapping("/createAccount")
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		return walletService.createAccount(name, mobileno, amount);
	}

	@GetMapping("/showBalance")
	public Customer showBalance(String mobileno) {
		return walletService.showBalance(mobileno);
	}

	@PutMapping("/fundTransfer")
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	}

	@PutMapping("/depositAmount")
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		return walletService.depositAmount(mobileNo, amount);
	}

	@PutMapping("/withdrawAmount")
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		return walletService.withdrawAmount(mobileNo, amount);
	}

	@GetMapping("/getList")
	public List<Customer> getList() {
		return walletService.getList();
	}

	@PutMapping("/updateAccount")
	public Customer updateAccount(Customer customer) {
		return walletService.updateAccount(customer);
	}

	@PutMapping("/addMoney/")
	public Customer addMoney(Wallet wallet, double amount) {
		return walletService.addMoney(wallet, amount);
	}
	
}