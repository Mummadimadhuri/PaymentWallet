package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.dto.BankAccountDTO;

public class AccountUtils {

	public static List<BankAccountDTO> convertToBankAccountDtoList(List<BankAccount> list){
		List<BankAccountDTO> dtoList = new ArrayList<BankAccountDTO>();
		for(BankAccount BankAccount : list)
			dtoList.add(convertToBankAccountDto(BankAccount));
		return dtoList;
	}
	
	public static BankAccount convertToBankAccount(BankAccountDTO dto) {
		BankAccount bankAcc = new BankAccount();
		bankAcc.setBalance(dto.getBalance());
		bankAcc.setBankName(dto.getBankName());
		bankAcc.setIfscCode(dto.getIfscCode());
		bankAcc.setWallet(dto.getWallet());
		return bankAcc;
	}
	
	public static BankAccountDTO convertToBankAccountDto(BankAccount bankAcc) {
		BankAccountDTO dto = new BankAccountDTO();
		
		dto.setBalance(bankAcc.getBalance());
		dto.setBankName(bankAcc.getBankName());
		dto.setIfscCode(bankAcc.getIfscCode());
		dto.setWallet(bankAcc.getWallet());
		
		return dto;
	}
}
