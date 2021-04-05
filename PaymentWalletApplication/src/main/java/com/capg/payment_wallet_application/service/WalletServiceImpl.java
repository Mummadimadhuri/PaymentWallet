package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.CustomerUtils;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo walletRepo;

	String invalidMobileNo = "Mobile number should be a 10 digit number with first digit from 6 to 9";
	String unregisteredMobileNo = "Mobile number is not registered to any customer";

	@Override
	public CustomerDTO createAccount(String name, String mobileno, BigDecimal amount)
			throws ConstraintViolationException {
		Customer customer = new Customer(name, mobileno);
		Wallet wallet = customer.getWallet();
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		return CustomerUtils.convertToCustomerDto(walletRepo.save(customer));
	}

	@Override
	public CustomerDTO showBalance(String mobileno) {
		Customer customer = null;
		if (mobileNoValidation(mobileno)) {
			customer = walletRepo.findOne(mobileno);
		} else {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (customer == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		return CustomerUtils.convertToCustomerDto(customer);
	}

	@Override
	public CustomerDTO fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		if (!mobileNoValidation(sourceMobileNo)) {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (!mobileNoValidation(sourceMobileNo)) {
			throw new InvalidInputException(invalidMobileNo);
		}
		Customer source = walletRepo.findByMobileNo(sourceMobileNo);
		Customer target = walletRepo.findByMobileNo(targetMobileNo);
		if (source == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		if (target == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		Wallet sourceWallet = source.getWallet();
		Wallet targetWallet = target.getWallet();
		if (sourceWallet.getBalance().compareTo(amount) >= 0) {
			throw new InsufficientBalanceException("Source doesn't have enough balance");
		}
		sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
		targetWallet.setBalance(targetWallet.getBalance().add(amount));
		walletRepo.save(source);
		walletRepo.save(target);
		return CustomerUtils.convertToCustomerDto(source);
	}

	@Override
	public CustomerDTO depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = null;
		if (mobileNoValidation(mobileNo)) {
			customer = walletRepo.findByMobileNo(mobileNo);
		} else {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (customer == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		walletRepo.save(customer);
		return CustomerUtils.convertToCustomerDto(customer);
	}

	@Override
	public CustomerDTO withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = null;
		if (mobileNoValidation(mobileNo)) {
			customer = walletRepo.findByMobileNo(mobileNo);
		} else {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (customer == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		BigDecimal currentBalance = customer.getWallet().getBalance();
		if (currentBalance.compareTo(amount) >= 0) {
			currentBalance = currentBalance.subtract(amount);
		} else {
			throw new InsufficientBalanceException(currentBalance.toString() + " is only available, You are "
					+ amount.subtract(currentBalance) + " short.");
		}
		customer.getWallet().setBalance(currentBalance);
		walletRepo.save(customer);
		return CustomerUtils.convertToCustomerDto(customer);
	}

	@Override
	public List<CustomerDTO> getList() {
		List<Customer> list = walletRepo.getList();
		return CustomerUtils.convertToCustomerDtoList(list);
	}

	@Override
	public CustomerDTO updateAccount(Customer customer) {
		walletRepo.save(customer);
		return CustomerUtils.convertToCustomerDto(customer);
	}

	@Override
	public CustomerDTO addMoney(Wallet wallet, double amount) {
		Customer customer = walletRepo.findByWallet(wallet);
		wallet = customer.getWallet();
		wallet.setBalance(wallet.getBalance().add(BigDecimal.valueOf(amount)));
		customer.setWallet(wallet);
		walletRepo.save(customer);
		return CustomerUtils.convertToCustomerDto(customer);
	}

	private static boolean mobileNoValidation(String mobileNo) {
		boolean flag = false;
		if (Pattern.matches("^[6-9][0-9]{9}$", mobileNo)) {
			flag = true;
		}
		return flag;
	}
}
