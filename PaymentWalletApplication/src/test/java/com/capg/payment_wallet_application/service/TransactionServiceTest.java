package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;

@SpringBootTest
class TransactionServiceTest {

	@Autowired
	TransactionService transactionService;

	@Test
	void testAddTransaction() {
		BigDecimal balance = new BigDecimal(10000);
		LocalDate Date = LocalDate.parse("2020-08-01");
		Transaction tran = new Transaction("SEND", Date, new Wallet(balance), 5000, "String");
		TransactionDTO transaction = transactionService.addTransaction(tran);
		assertNotNull(transaction);
	}

	@Test
	void testViewAllTransactionsWallet() {
		BigDecimal balance = new BigDecimal(10000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(17);
		List<TransactionDTO> tran = transactionService.viewAllTransactions(wallet);
		assertNotNull(tran);
	}

	@Test
	void testViewAllTransactionsString() {
		List<TransactionDTO> tran = transactionService.viewAllTransactions("SEND");
		assertNotNull(tran);
	}

	@Test
	void testViewTransactionsByDate() {
		LocalDate from = LocalDate.parse("2000-07-04");
		LocalDate to = LocalDate.parse("2020-08-01");
		List<TransactionDTO> tran = transactionService.viewTransactionsByDate(from, to);
		assertNotNull(tran);
	}

}