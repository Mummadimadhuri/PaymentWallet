package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.dto.BankAccountDTO;

public class AccountUtils {

	private AccountUtils() {
		
	}
	
	public static List<BankAccountDTO> convertToBankAccountDtoList(List<BankAccount> list){
		List<BankAccountDTO> dtoList = new ArrayList<>();
		for(BankAccount BankAccount : list)
			dtoList.add(convertToBankAccountDto(BankAccount));
		return dtoList;
	}
	
	public static BankAccount convertToBankAccount(BankAccountDTO dto) {
		BankAccount bankAcc = new BankAccount();
		bankAcc.setBalance(dto.getBalance());
		bankAcc.setBankName(dto.getBankName());
		bankAcc.setIfscCode(dto.getIfscCode());
		bankAcc.setWallet(WalletUtils.convertToWallet(dto.getWalletDto()));
		return bankAcc;
	}
	
	public static BankAccountDTO convertToBankAccountDto(BankAccount bankAcc) {
		BankAccountDTO dto = new BankAccountDTO();
		
		dto.setBalance(bankAcc.getBalance());
		dto.setBankName(bankAcc.getBankName());
		dto.setIfscCode(bankAcc.getIfscCode());
		dto.setWalletDto(WalletUtils.convertToWalletDto(bankAcc.getWallet()));
		
		return dto;
	}
}
