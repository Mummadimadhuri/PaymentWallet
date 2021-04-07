package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;

@SpringBootTest
class AccountServiceImplTest {

	@Autowired
	AccountServiceImpl accountService;
	
	@Test
	void testAddAccount() {
		BigDecimal balance = new BigDecimal(20000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(40);
		BankAccount bankAccount = new BankAccount("SBIN0000023","STATEBANK",20000,wallet);
		WalletDTO bankAcc = accountService.addAccount(bankAccount);
		assertNotNull(bankAcc);
	}

	@Test
	void testRemoveAccount() {
		BigDecimal balance = new BigDecimal(22000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(25);
		BankAccount bankacc = new BankAccount("SBIN100023","STATEBANK",23000,wallet);
		WalletDTO bankAccount =accountService.removeAccount(bankacc);
		assertNotNull(bankAccount);
	}

	@Test
	void testViewAllAccounts() {
		List<BankAccountDTO> bankAcc = accountService.viewAllAccounts(40);
		assertNotNull(bankAcc);
	}

	@Test
	void testViewAccount() {
		WalletDTO wallet= accountService.viewAccount(86,"SBIN0000134");
		assertNotNull(wallet);
	}

}
