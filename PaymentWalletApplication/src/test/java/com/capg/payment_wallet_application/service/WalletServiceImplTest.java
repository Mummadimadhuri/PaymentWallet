package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.util.BeneficiaryDetailsUtils;
import com.capg.payment_wallet_application.util.TransactionUtils;
import com.capg.payment_wallet_application.util.WalletUtils;

@SpringBootTest
class WalletServiceImplTest {
	
	@Autowired
	private WalletServiceImpl walletService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private BenificiaryService benificiaryService;

	@Disabled
	@Test
	void testCreateAccount() {
		BigDecimal amount = new BigDecimal(10000), amount1 = new BigDecimal(0);
		String name = "Arun Kumar", name1 = "Arun Kumar1";
		String mobileNo = "9876543210", mobileNo1 = "9876543211", mobileNo2 = "9876543212",
				mobileNo3 = "9876543213",mobileNo4 = "98765432101", mobileNo5 = "987654321a";
		String password = "Arun@2000", password1 = "Arun2000";
		
		CustomerDTO createAccount = walletService.createAccount(name,mobileNo,amount,password);
		assertTrue(name.equals(createAccount.getName()));
		assertTrue(mobileNo.equals(createAccount.getMobileNo()));
		assertEquals(amount,createAccount.getWalletDto().getBalance());
		assertNotNull(createAccount);
		
		assertThrows(InvalidInputException.class, 
				()->walletService.createAccount(name,mobileNo,amount,password));
		assertThrows(TransactionSystemException.class, 
				()->walletService.createAccount(name1,mobileNo1,amount,password));
		assertThrows(InsufficientBalanceException.class, 
				()->walletService.createAccount(name,mobileNo2,amount1,password));
		assertThrows(InvalidInputException.class, 
				()->walletService.createAccount(name,mobileNo3,amount,password1));
		assertThrows(TransactionSystemException.class, 
				()->walletService.createAccount(name,mobileNo4,amount,password));
		assertThrows(TransactionSystemException.class, 
				()->walletService.createAccount(name,mobileNo5,amount,password));
	}

	@Disabled
	@Test
	void testShowBalance() {
		walletService.createAccount("Arun Kumar M","8765432190",new BigDecimal(1000),"Arun@2000");
		
		String mobileNo = "8765432190", mobileNo1 = "876543211", 
				mobileNo2 = "98765432101", mobileNo3 = "984229186a";
		
		CustomerDTO balance = walletService.showBalance(mobileNo);
		assertNotNull(balance);
		assertTrue(mobileNo.equals(balance.getMobileNo()));
		
		assertThrows(InvalidInputException.class, ()->walletService.showBalance(mobileNo1));
		assertThrows(InvalidInputException.class, ()->walletService.showBalance(mobileNo2));
		assertThrows(InvalidInputException.class, ()->walletService.showBalance(mobileNo3));
	}

	@Disabled
	@Test
	void testFundTransfer() {
		walletService.createAccount("Arun Kumar M","7654321980",new BigDecimal(1000),"Arun@2000");
		walletService.createAccount("Madhuri","7654321981",new BigDecimal(1000),"Madhuri@2000");
		
		BigDecimal amount = new BigDecimal(100), amount1 = new BigDecimal(-1);
		String sourceMobileNo = "7654321980", sourceMobileNo1 = "765432198a"; 
		String targetMobileNo = "7654321981", targetMobileNo1 = "765432198b";
		
		BigDecimal sourceOldBalance = walletService.showBalance(sourceMobileNo).getWalletDto().getBalance();
		BigDecimal targetOldBalance = walletService.showBalance(targetMobileNo).getWalletDto().getBalance();
		CustomerDTO balance = walletService.fundTransfer(sourceMobileNo,targetMobileNo,amount);
		BigDecimal sourceNewBalance = walletService.showBalance(sourceMobileNo).getWalletDto().getBalance();
		BigDecimal targetNewBalance = walletService.showBalance(targetMobileNo).getWalletDto().getBalance();
		Transaction sourceTransaction = 
				new Transaction("SEND",LocalDate.now(),
						WalletUtils.convertToWallet(walletService.showBalance(sourceMobileNo).getWalletDto()),
						Double.parseDouble(amount.toString()),
						"Sending "+amount+" to "+targetMobileNo);
		Transaction targetTransaction = 
				new Transaction("RECEIVE",LocalDate.now(),
						WalletUtils.convertToWallet(walletService.showBalance(targetMobileNo).getWalletDto()),
						Double.parseDouble(amount.toString()),
						"Receiving "+amount+" from "+sourceMobileNo);
		BenificiaryDetails benificiary = 
				new BenificiaryDetails(
						walletService.showBalance(targetMobileNo).getName(),
						walletService.showBalance(targetMobileNo).getMobileNo());
		benificiary.setWallet(WalletUtils.convertToWallet(walletService.showBalance(sourceMobileNo).getWalletDto()));
		Transaction sourceTransaction1 = TransactionUtils.convertToTransaction(transactionService.viewAllTransactions(
				walletService.showBalance(sourceMobileNo).getWalletDto().getWalletId()).get(0));
		Transaction targetTransaction1 = TransactionUtils.convertToTransaction(transactionService.viewAllTransactions(
				walletService.showBalance(targetMobileNo).getWalletDto().getWalletId()).get(0));
		BenificiaryDetails benificiary1 = 
				BeneficiaryDetailsUtils.convertToBenificiaryDetails(benificiaryService.viewBenificiary(targetMobileNo));
		
		assertNotNull(balance);
		assertTrue(sourceOldBalance.subtract(amount).compareTo(sourceNewBalance)==0);
		assertTrue(targetOldBalance.add(amount).compareTo(targetNewBalance)==0);
		assertTrue(sourceTransaction.getTransactionType().equals(sourceTransaction1.getTransactionType()));
		assertTrue(targetTransaction.getTransactionType().equals(targetTransaction1.getTransactionType()));
		assertEquals(sourceTransaction.getTransactionDate(),sourceTransaction1.getTransactionDate());
		assertEquals(targetTransaction.getTransactionDate(),targetTransaction1.getTransactionDate());
		assertEquals(benificiary.getName(),benificiary1.getName());
		assertEquals(benificiary.getMobileNumber(),benificiary1.getMobileNumber());
		assertEquals(benificiary.getWallet().getWalletId(),benificiary1.getWallet().getWalletId());
		
		assertThrows(InvalidInputException.class,()->walletService.fundTransfer(sourceMobileNo1,targetMobileNo,amount));
		assertThrows(InvalidInputException.class,()->walletService.fundTransfer(sourceMobileNo,targetMobileNo1,amount));
		assertThrows(InsufficientBalanceException.class,()->walletService.fundTransfer(sourceMobileNo,targetMobileNo,amount1));
	}
}