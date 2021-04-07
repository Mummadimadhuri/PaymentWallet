package com.capg.payment_wallet_application.service;

import java.util.List;
import java.util.regex.Pattern;

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

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	IAccountRepository accountRepo;

	@Override
	public WalletDTO addAccount(BankAccount bacc) {
		accountRepo.save(bacc);
		Wallet wallet = bacc.getWallet();
		return WalletUtils.convertToWalletDto(wallet);
	}

	@Override
	public WalletDTO removeAccount(BankAccount bacc) {
		Wallet wallet = bacc.getWallet();
		accountRepo.delete(bacc);
		return WalletUtils.convertToWalletDto(wallet);
	}

	@Override
	public List<BankAccountDTO> viewAllAccounts(int walletId) {
		List<BankAccount> bankAccountList = accountRepo.findByWalletId(walletId);
		return AccountUtils.convertToBankAccountDtoList(bankAccountList);
	}

	@Override
	public WalletDTO viewAccount(int accountNo, String ifscCode) {
		if(validateIfscCode(ifscCode)) {
			throw new InvalidInputException("IFSC code must have 4 alphabets followed by 7 numbers total 11 characters");
		}
		AccountId id = new AccountId(accountNo,ifscCode);
		BankAccount bankAccount = accountRepo.findById(id).orElse(null);
		if(bankAccount==null) {
			throw new InvalidInputException("Wrong credentials");
		}
		Wallet wallet = bankAccount.getWallet();
		return WalletUtils.convertToWalletDto(wallet);
	}
	
	private boolean validateIfscCode(String ifscCode) {
		boolean flag = true;
		if(!Pattern.matches("^[A-Z]{4}[0-9]{7}$", ifscCode)) {
			flag = false;
		}
		return flag;
	}
}
