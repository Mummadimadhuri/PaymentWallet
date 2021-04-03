package com.capg.payment_wallet_application.Controller;

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
import com.capg.payment_wallet_application.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@PostMapping("/createAccount/{name}/{mobileno}/{amount}")
	public Customer createAccount(@PathVariable String name,@PathVariable String mobileno,@PathVariable BigDecimal amount) {
		return walletService.createAccount(name, mobileno, amount);
	}
	
	@GetMapping("/showBalance/{mobileno}")
	public Customer showBalance(@PathVariable String mobileno) {
		return walletService.showBalance(mobileno);
	}

	@PutMapping("/fundTransfer/{sourceMobileNo}/{targetMobileNo}/{amount}")
	public Customer fundTransfer(@PathVariable String sourceMobileNo,@PathVariable String targetMobileNo,@PathVariable BigDecimal amount) {
		return walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount);
	}

	@PutMapping("/depositAmount/{mobileNo}/{amount}")
	public Customer depositAmount(@PathVariable String mobileNo,@PathVariable BigDecimal amount) {
		return walletService.depositAmount(mobileNo, amount);
	}

	@PutMapping("/withdrawAmount/{mobileNo}/{amount}")
	public Customer withdrawAmount(@PathVariable String mobileNo,@PathVariable BigDecimal amount) {
		return walletService.withdrawAmount(mobileNo, amount);
	}

	@GetMapping("/getList")
	public List<Customer> getList() {
		return walletService.getList();
	}

	@PutMapping("/updateAccount")
	public Customer updateAccount(@RequestBody Customer customer) {
		return walletService.updateAccount(customer);
	}

	@PutMapping("/addMoney/{amount}")
	public Customer addMoney(@RequestBody Wallet wallet,@PathVariable double amount) {
		return walletService.addMoney(wallet, amount);
	}
	
}