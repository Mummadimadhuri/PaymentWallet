package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BankAccountDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;

@SpringBootTest
class AccountServiceImplTest {

	@Autowired
	private AccountServiceImpl accountService;

	@Test
	void testAddAccount() {
		BigDecimal balance = new BigDecimal(20000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(169);
		String ifscCode = "SBIN0000023";
		String ifscCode1 = "SBIn0000023";
		String bankName = "STATEBANK";
		String bankName2 = "STATEBANK 23554 availale in india which is held by governement,Its normally very rush peak hours";
		double amount = 23000;
		double amount1 = 500;

		BankAccount bankAccount = new BankAccount(ifscCode, bankName, amount, wallet);
		WalletDTO bankAcc = accountService.addAccount(bankAccount);
		assertNotNull(bankAcc);
		assertTrue(ifscCode.equals(bankAccount.getIfscCode()));
		assertEquals(wallet.getWalletId(), bankAcc.getWalletId());
		assertDoesNotThrow(() -> accountService.addAccount(bankAccount));

		BankAccount bankAccount1 = new BankAccount(ifscCode1, bankName, amount, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount1));
		BankAccount bankAccount2 = new BankAccount(ifscCode, bankName2, amount, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount2));
		BankAccount bankAccount3 = new BankAccount(ifscCode, bankName, amount1, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount3));
	}

	@Test
	void testRemoveAccount() {
		BigDecimal balance = new BigDecimal(20000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(40);
		String ifscCode = "SBIN0000023";
		String bankName = "STATEBANK";
		String ifscCode1 = "SBIn00023";
		double amount = 20000;
		double amount1 = 500;

		BankAccount bankacc = new BankAccount(ifscCode, bankName, amount, wallet);
		WalletDTO bankAccount = accountService.removeAccount(bankacc);
		assertNotNull(bankAccount);
		assertEquals(wallet.getWalletId(), bankAccount.getWalletId());
		
		BankAccount bankacc1 = new BankAccount(ifscCode, bankName, amount, wallet);
		assertDoesNotThrow(() -> accountService.removeAccount(bankacc1));

		BankAccount bankacc3 = new BankAccount(ifscCode1, bankName, amount1, wallet);
		assertThrows(InvalidInputException.class, () -> accountService.removeAccount(bankacc3));
	}

	@Test
	void testViewAllAccounts() {
		int walletId = 169;
		int walletId1 = 1;

		List<BankAccountDTO> bankAcc = accountService.viewAllAccounts(walletId);
		assertDoesNotThrow(() -> accountService.viewAllAccounts(walletId));
		assertNotNull(bankAcc);
		assertThrows(WalletNotFoundException.class, () -> accountService.viewAllAccounts(walletId1));
	}

	@Test
	void testViewAccount() {
		int accountNumber = 87;
		String ifscCode = "SBIN0000148";
		int accountNumber1 = 88;
		String ifscCode1 = "SBIN0000145";
		int accountNumber2 = 89;
		String ifscCode2 = "SBIn0000143";

		WalletDTO wallet = accountService.viewAccount(accountNumber, ifscCode);
		assertEquals(74, wallet.getWalletId());
		assertDoesNotThrow(() -> accountService.viewAccount(accountNumber, ifscCode));

		assertThrows(InvalidInputException.class, () -> accountService.viewAccount(accountNumber1, ifscCode1));
		assertThrows(InvalidInputException.class, () -> accountService.viewAccount(accountNumber2, ifscCode2));
		assertNotNull(wallet);
	}
}
