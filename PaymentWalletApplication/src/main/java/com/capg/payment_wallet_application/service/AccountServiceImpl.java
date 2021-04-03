package com.capg.payment_wallet_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.repo.IAccountRepository;
import com.capg.payment_wallet_application.util.WalletUtils;

@Service
public class AccountServiceImpl implements IAccountService{
	@Autowired
	IAccountRepository accountRepo;
	@Override
	public WalletDTO addAccount(BankAccount bacc) {
		// TODO Auto-generated method stub
		Wallet wallet = bacc.getWallet();
		return WalletUtils.convertToWalletDto(accountRepo.save(wallet));
	}

	@Override
	public WalletDTO removeAccount(BankAccount bacc) {
		// TODO Auto-generated method stub
		Wallet wallet = bacc.getWallet();
		Wallet walletContainer = wallet;
		accountRepo.delete(wallet);
		return WalletUtils.convertToWalletDto(walletContainer);
	}

	@Override
	public List<WalletDTO> viewAllAccounts(Wallet wallet) {
		List<Wallet> walletList=accountRepo.findByWallet(wallet);
		List<WalletDTO> walletDtoList=WalletUtils.convertToWalletDtoList(walletList);
		
		return walletDtoList;
	}

	@Override
	public WalletDTO viewAccount(BankAccount bacc) {
		// TODO Auto-generated method stub
		Wallet wallet = accountRepo.findByBankAccount(bacc);
		
		WalletDTO walletDto = WalletUtils.convertToWalletDto(wallet);
		
		return walletDto;
	}

}
