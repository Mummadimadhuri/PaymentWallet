package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.repo.WalletRepo;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepo walletRepo;

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		Customer customer = new Customer(name,mobileno);
		customer.getWallet().setBalance(amount);
		return walletRepo.save(customer);
	}

	@Override
	public Customer showBalance(String mobileno) {
		return walletRepo.findOne(mobileno);
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		Customer source = walletRepo.findByMobileNo(sourceMobileNo);
		Customer target = walletRepo.findByMobileNo(targetMobileNo);
		source.getWallet().setBalance(source.getWallet().getBalance().subtract(amount));
		target.getWallet().setBalance(target.getWallet().getBalance().add(amount));
		return source;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = walletRepo.findByMobileNo(mobileNo);
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = walletRepo.findByMobileNo(mobileNo);
		BigDecimal currentBalance = customer.getWallet().getBalance();
		if(currentBalance.compareTo(amount)>=0) {
			currentBalance.subtract(amount);
		}else {
			throw new InsufficientBalanceException(currentBalance.toString()+"is only available");
		}
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public List<Customer> getList() {
		return walletRepo.getList();
	}

	@Override
	public Customer updateAccount(Customer customer) {
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) {
		Customer customer = walletRepo.findByWallet(wallet);
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(BigDecimal.valueOf(amount)));
		walletRepo.save(customer);
		return customer;
	}
	
	

}
