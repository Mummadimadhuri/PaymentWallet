package com.capg.payment_wallet_application.service;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.payment_wallet_application.beans.AccountId;
import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.IAccountRepository;
import com.capg.payment_wallet_application.util.AccountUtils;
import com.capg.payment_wallet_application.util.WalletUtils;
/**
 * Author: Bhavish Reddy
 * Version: 1.0
 * Date: 03-04-2021
 * Description: This is Account Service Implementation
 */
@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountRepository accountRepo;
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * Description: Add Bank Account
	 * Input Parameters: Bank Account Object
	 * Return value: Wallet Data Transfer Object
	 */
	@Override
	public WalletDTO addAccount(BankAccount bankAccount) {
		logger.info("addAccount() is get intiated");
		accountRepo.save(bankAccount);
		Wallet wallet = bankAccount.getWallet();
		logger.info("addAccount() is get executed");
		return WalletUtils.convertToWalletDto(wallet);
	}

	/**
	 * Description: Remove Bank Account
	 * Input Parameters: Bank Account Object
	 * Return value: Wallet Data Transfer Object
	 */
	@Override
	public WalletDTO removeAccount(BankAccount bankAccount) {
		logger.info("removeAccount() is get intiated");
		Wallet wallet = bankAccount.getWallet();
		accountRepo.delete(bankAccount);
		logger.info("removeAccount() is get exectued");
		return WalletUtils.convertToWalletDto(wallet);
	}
	
	/**
	 * Description: View all accounts linked to a wallet
	 * Input Parameters: int walletId
	 * Return value: List of Bank Accounts
	 */
	@Override
	public List<BankAccountDTO> viewAllAccounts(int walletId) {
		logger.info("viewAllAccounts() is get intiated");
		List<BankAccount> bankAccountList = accountRepo.findByWalletId(walletId);
		logger.info("viewAllAccounts() is get executed");
		return AccountUtils.convertToBankAccountDtoList(bankAccountList);
	}
	/**
	 * Description: View wallet linked with Bank Account
	 * Input Parameters: int accountNo, String ifscCode
	 * Return value: Wallet Data Transfer Object
	 * Exception: InvalidInputException
	 */
	@Override
	public WalletDTO viewAccount(int accountNo, String ifscCode) {
		logger.info("viewAccount() is get intiated");
		if(validateIfscCode(ifscCode)) {
			throw new InvalidInputException("IFSC code must have 4 alphabets followed by 7 numbers total 11 characters");
		}
		AccountId id = new AccountId(accountNo,ifscCode);
		BankAccount bankAccount = accountRepo.findById(id).orElse(null);
		if(bankAccount==null) {
			throw new InvalidInputException("Wrong credentials");
		}
		Wallet wallet = bankAccount.getWallet();
		logger.info("viewAccount() is get executed");
		return WalletUtils.convertToWalletDto(wallet);
	}
	/**
	 * Description: Validate IFSC code
	 * Input Parameters: String ifscCode
	 * Return value: boolean flag
	 */
	private boolean validateIfscCode(String ifscCode) {
		logger.info("validateIfscCode() validation is start intiated");
		boolean flag = true;
		if(!Pattern.matches("^[A-Z]{4}[0-9]{7}$", ifscCode)) {
		flag = false;
		}
		logger.info("validateIfscCode() validation is  get executed");
		return flag;
	}
}
