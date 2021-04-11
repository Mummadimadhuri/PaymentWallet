package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.WalletDTO;

public class WalletUtils {

	private WalletUtils() {

	}

	public static List<WalletDTO> convertToWalletDtoList(List<Wallet> list) {
		List<WalletDTO> dtolist = new ArrayList<>();
		for (Wallet Wallet : list)
			dtolist.add(convertToWalletDto(Wallet));
		return dtolist;
	}

	public static Wallet convertToWallet(WalletDTO walletDto) {
		Wallet wallet = new Wallet();
		wallet.setWalletId(walletDto.getWalletId());
		wallet.setBalance(walletDto.getBalance());
		return wallet;
	}

	public static WalletDTO convertToWalletDto(Wallet wallet) {
		WalletDTO walletDto = new WalletDTO();
		walletDto.setWalletId(wallet.getWalletId());
		walletDto.setBalance(wallet.getBalance());
		return walletDto;
	}

}
