package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;

@SpringBootTest
class TransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	void testAddTransaction() {
		logger.info("testAddTransaction() is get intiated");
		BigDecimal balance = new BigDecimal(10000),balance1 = new BigDecimal(0),balance3 = new BigDecimal(25000);
		LocalDate date = LocalDate.parse("2020-08-01");
	    String transactionType1 = "SEND";
	    String transactionType2 = "RECEIVE";
	    String transactionType3 = "NULL";
        double amount = 3000;
        double amount1 = 0;
        double amount2 = 20000;
        BigDecimal amount3 = BigDecimal.valueOf(amount);
        BigDecimal updatedBalance = balance.subtract(amount3); 
        
        Transaction tran = new Transaction(transactionType1,date,new Wallet(balance),amount,"Description");
        Transaction tran1 = new Transaction(transactionType2,date,new Wallet(balance),amount,"Description");
		TransactionDTO transaction = transactionService.addTransaction(tran);
		TransactionDTO transaction1 = transactionService.addTransaction(tran1);
		assertTrue(transactionType2.equals(transaction1.getTransactionType()));
		assertTrue(transactionType1.equals(transaction.getTransactionType()));
		assertEquals(updatedBalance,transaction.getWalletDto().getBalance());
		assertEquals(date,transaction.getTransactionDate());
		assertNotNull(transaction);
		
		Transaction tran3 = new Transaction(transactionType3,date,new Wallet(balance3),amount,"String");
		assertThrows(InvalidInputException.class,() -> transactionService.addTransaction(tran3));
		Transaction tran4 = new Transaction(transactionType1,date,new Wallet(balance),amount2,"string");
		assertThrows(InsufficientBalanceException.class,() -> transactionService.addTransaction(tran4));
		Transaction tran5 = new Transaction(transactionType1,date,new Wallet(balance),amount1,"string");
		assertThrows(TransactionSystemException.class,() -> transactionService.addTransaction(tran5));
		Transaction tran6 = new Transaction(transactionType1,date,new Wallet(balance1),amount1,"string");
		assertThrows(TransactionSystemException.class,() -> transactionService.addTransaction(tran6));
		logger.info("testAddTransaction() is get executed");
	}
	

	@Test
	void testViewAllTransactionsWallet() {
		logger.info("viewAllTransactionsWallet() is get intiated");
	    int walletId = 169;
	    int walletId1 = 40;
	    int walletId2 = 17;
	    
	     List<TransactionDTO> tran = transactionService.viewAllTransactions(walletId);
		 assertNotNull(tran);
		 List<TransactionDTO> tran1 = transactionService.viewAllTransactions(walletId1);
		 assertNotNull(tran1);
         
		 assertThrows(WalletNotFoundException.class,() -> transactionService.viewAllTransactions(walletId2));
		 logger.info("viewAllTransactionsWallet() is get closed");
	}

	@Test
	void testViewAllTransactionsString() {
		logger.info("ViewAllTransactionsString() is get intiated");
		String transactionType = "SEND";
		String transactionType1 ="RECEIVE";
		String transactionType2 ="Null";
		
		List<TransactionDTO> tran = transactionService.viewAllTransactions(transactionType);
		assertNotNull(tran);
		assertDoesNotThrow(()->transactionService.viewAllTransactions(transactionType1));
		
	    assertThrows(InvalidInputException.class,()->transactionService.viewAllTransactions(transactionType2));
	    logger.info("ViewAllTransactionsString() is get executed");
	}

	@Test
	void testViewTransactionsByDate() {
		logger.info("ViewtransactionByDate() is get intiated()");
		LocalDate from = LocalDate.parse("2000-07-04");
		LocalDate to = LocalDate.parse("2020-08-01");
		LocalDate from1 = LocalDate.parse("2020-07-01");
		LocalDate to1 = LocalDate.parse("2019-08-01");
		
		List<TransactionDTO> tran = transactionService.viewTransactionsByDate(from, to);
		assertNotNull(tran);
		assertDoesNotThrow(()->transactionService.viewTransactionsByDate(from, to));
		
		assertThrows(InvalidInputException.class,()->transactionService.viewTransactionsByDate(from1, to1));
		logger.info("ViewtransactionByDate() is get executed");
	}

}