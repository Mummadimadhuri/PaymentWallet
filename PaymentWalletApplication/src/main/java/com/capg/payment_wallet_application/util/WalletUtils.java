package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.WalletDTO;

public class WalletUtils {

	public static List<WalletDTO> convertToWalletDtoList(List<Wallet> list) {
		List<WalletDTO> dtolist = new ArrayList<WalletDTO>();
		for (Wallet Wallet : list)
			dtolist.add(convertToWalletDto(Wallet));
		return dtolist;
	}

	public static Wallet convertToWallet(WalletDTO dto) {
		Wallet wallet = new Wallet();
        wallet.setWalletId(dto.getWalletId());
		wallet.setBalance(dto.getBalance());
		return wallet;
	}

	public static WalletDTO convertToWalletDto(Wallet wallet) {
		WalletDTO walletdto = new WalletDTO();
		walletdto.setWalletId(walletdto.getWalletId());
		walletdto.setBalance(walletdto.getBalance());
		return walletdto;
	}

}
