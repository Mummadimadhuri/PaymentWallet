package com.capg.payment_wallet_application.service;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.payment_wallet_application.beans.AccountId;
import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.repo.IAccountRepository;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.AccountUtils;
import com.capg.payment_wallet_application.util.WalletUtils;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository accountRepo;
	
	@Autowired
	WalletRepo walletRepo;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public WalletDTO addAccount(BankAccount bacc) {
		logger.info("addAccount() is get intiated");
		accountRepo.save(bacc);
		Wallet wallet = bacc.getWallet();
		logger.info("addAccount() is get executed");
		return WalletUtils.convertToWalletDto(wallet);
	}

	@Override
	public WalletDTO removeAccount(BankAccount bacc) {
		logger.info("removeAccount() is get intiated");
		AccountId id = new AccountId(bacc.getAccountNo(),bacc.getIfscCode());
		BankAccount bankAcc = accountRepo.findById(id).orElse(null);
		if(bankAcc != null)
		{
		Wallet wallet = bacc.getWallet();
		accountRepo.delete(bacc);
		logger.info("removeAccount() is get exectued");
		return WalletUtils.convertToWalletDto(wallet);
		}
		else
		{
			throw new InvalidInputException("Given Account no is not present");
		}
	}

	@Override
	public List<BankAccountDTO> viewAllAccounts(int walletId) {
		logger.info("viewAllAccounts() is get intiated");
		Customer wallet =  walletRepo.findByWalletId(walletId);
		if(wallet != null)
		{
		List<BankAccount> bankAccountList = accountRepo.findByWalletId(walletId);
		logger.info("viewAllAccounts() is get executed");
		return AccountUtils.convertToBankAccountDtoList(bankAccountList);
		}
		else
		{
			throw new WalletNotFoundException("Given wallet is not found");
		}
	}

	@Override
	public WalletDTO viewAccount(int accountNo, String ifscCode) {
		logger.info("viewAccount() is get intiated");
		if(!validateIfscCode(ifscCode)) {
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
