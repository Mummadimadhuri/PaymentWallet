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
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.dto.WalletDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.util.WalletUtils;

@SpringBootTest
class AccountServiceImplTest {

	@Autowired
	private AccountServiceImpl accountService;

	@Autowired
	private WalletServiceImpl walletService;
	
	@Test
	void testAddAccount() {
		String ifscCode = "SBIN0000023";
		String ifscCode1 = "SBIn0000023";
		String bankName = "STATEBANK";
		String bankName2 = "STATEBANK 23554 availale in india which is held by governement,Its normally very rush peak hours";
		int accountNo = 1256;
		double amount = 23000;
		double amount1 = 500;
		CustomerDTO customer = walletService.createAccount("Bhavish", "9998765432", new BigDecimal(1000), "Bhavish@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());	
		BankAccount bankAccount = new BankAccount(accountNo,ifscCode, bankName, amount, wallet);
		WalletDTO bankAccWallet = accountService.addAccount(bankAccount);
		assertNotNull(bankAccWallet);
		assertEquals(wallet.getWalletId(), bankAccWallet.getWalletId());

		BankAccount bankAccount1 = new BankAccount(accountNo,ifscCode1, bankName, amount, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount1));
		BankAccount bankAccount2 = new BankAccount(accountNo,ifscCode, bankName2, amount, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount2));
		BankAccount bankAccount3 = new BankAccount(accountNo,ifscCode, bankName, amount1, wallet);
		assertThrows(TransactionSystemException.class, () -> accountService.addAccount(bankAccount3));
	}

	@Test
	void testRemoveAccount() {
		String ifscCode = "SBIN0000023";
		String bankName = "STATEBANK";
		String ifscCode1 = "SBIn00023";
		double amount = 20000;
		double amount1 = 500;
		int accountNo = 1257;
		CustomerDTO customer = walletService.createAccount("Bhavish", "9998765433", new BigDecimal(1000), "Bhavish@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());

		BankAccount bankacc = new BankAccount(accountNo,ifscCode, bankName, amount, wallet);
		accountService.addAccount(bankacc);
		bankacc.setAccountNo(accountService.viewAllAccounts(wallet.getWalletId()).get(0).getAccountNo());
		WalletDTO bankAccount = accountService.removeAccount(bankacc);
		assertNotNull(bankAccount);
		assertEquals(wallet.getWalletId(), bankAccount.getWalletId());
		assertTrue(accountService.viewAllAccounts(wallet.getWalletId()).size()==0);

		BankAccount bankacc3 = new BankAccount(accountNo,ifscCode1, bankName, amount1, wallet);
		assertThrows(InvalidInputException.class, () -> accountService.removeAccount(bankacc3));
	}

	@Test
	void testViewAllAccounts() {
		CustomerDTO customer = walletService.createAccount("Bhavish", "9998765434", new BigDecimal(1000), "Bhavish@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		int walletId = wallet.getWalletId();
		int walletId1 = 1000;
		List<BankAccountDTO> bankAccList = accountService.viewAllAccounts(walletId);
		assertNotNull(bankAccList);
		
		assertThrows(WalletNotFoundException.class, () -> accountService.viewAllAccounts(walletId1));
	}

	@Test
	void testViewAccount() {
		int accountNumber = 1258;
		CustomerDTO customer = walletService.createAccount("Bhavish", "9998765435", new BigDecimal(1000), "Bhavish@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		BankAccount bankAccount = new BankAccount(accountNumber,"SBIN0001234", "State Bank", 1000.0, wallet);
		WalletDTO walletDto = accountService.addAccount(bankAccount);
		String ifscCode = "SBIN0001234";
		
		int accountNumber1 = 1000;
		String ifscCode1 = "SBIN0000145";
		int accountNumber2 = 89;
		String ifscCode2 = "SBIn0000143";
		
		assertEquals(walletDto.getWalletId(), wallet.getWalletId());
		assertDoesNotThrow(() -> accountService.viewAccount(accountNumber, ifscCode));

		assertThrows(InvalidInputException.class, () -> accountService.viewAccount(accountNumber1, ifscCode1));
		assertThrows(InvalidInputException.class, () -> accountService.viewAccount(accountNumber2, ifscCode2));
	}

}
