package com.capg.payment_wallet_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
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
	public List<BankAccountDTO> viewAllAccounts(Wallet wallet) {
		List<BankAccount> bankAccountList = accountRepo.findByWallet(wallet);
		return AccountUtils.convertToBankAccountDtoList(bankAccountList);
	}

	@Override
	public WalletDTO viewAccount(BankAccount bacc) {
		BankAccount bankAccount = accountRepo.findByBankAccount(bacc);
		Wallet wallet = bankAccount.getWallet();
		return WalletUtils.convertToWalletDto(wallet);
	}

}
