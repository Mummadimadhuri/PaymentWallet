package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.TransactionSystemException;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;

@SpringBootTest
class BenificiaryServiceTest {

	@Autowired
	private BenificiaryService benificiaryService;
	
	@Test
	void testAddBenificiary() {
		BigDecimal amount = new BigDecimal(10000), amount1 = new BigDecimal(20000);
		Wallet wallet = new Wallet(amount), wallet1 = new Wallet(amount1);
		String name = "Ravi";
		String name1 = "Suresh";
		String mobileNumber = "9875623456";
		String mobileNumber2 = "9445891860566";

		BenificiaryDetails benificiary = new BenificiaryDetails(name, mobileNumber);
		wallet1.setWalletId(40);
		benificiary.setWallet(wallet1);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.addBenificiary(benificiary);
		assertNotNull(benificiaryDetails);
		assertEquals(mobileNumber, benificiaryDetails.getMobileNumber());
		assertDoesNotThrow(() -> benificiaryService.addBenificiary(benificiary));

		BenificiaryDetails benificiary2 = new BenificiaryDetails(name1, mobileNumber2);
		wallet1.setWalletId(40);
		benificiary2.setWallet(wallet1);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.addBenificiary(benificiary2));
	}

	@Test
	void testUpdateBenificiary() {
		BigDecimal amount = new BigDecimal(10000), amount1 = new BigDecimal(20000);
		Wallet wallet = new Wallet(amount), wallet1 = new Wallet(amount1);
		String name = "Ravi";
		String name1 = "Suresh";
		String mobileNumber = "9875623456";
		String mobileNumber2 = "9445891860566";

		BenificiaryDetails benificiary = new BenificiaryDetails(name1, mobileNumber);
		wallet1.setWalletId(40);
		benificiary.setWallet(wallet1);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.updateBenificiary(benificiary);
		assertEquals(name1, benificiaryDetails.getName());
		assertNotNull(benificiary);
		assertDoesNotThrow(() -> benificiaryService.updateBenificiary(benificiary));

		BenificiaryDetails benificiary2 = new BenificiaryDetails(name1, mobileNumber2);
		wallet1.setWalletId(40);
		benificiary2.setWallet(wallet1);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.addBenificiary(benificiary2));

	}

	@Test
	void testDeleteBenificiary() {
		String name = "Suresh";
		String mobileNumber = "9875623456";

		BenificiaryDetails benificiary = new BenificiaryDetails(name, mobileNumber);
		String response = benificiaryService.deleteBenificiary(benificiary);
		assertEquals("Benificiary Details is Deleted", response);

		BenificiaryDetails benificiary2 = new BenificiaryDetails();
		assertThrows(InvalidDataAccessApiUsageException.class,
				() -> benificiaryService.deleteBenificiary(benificiary2));
	}

	@Test
	void testViewBenificiary() {
		String mobileNumber = "9360043198";
		String mobileNumber1 = "57984438892839289";
		String mobileNumber2 = "9789269680";

		BenificiaryDetailsDTO benificiary = benificiaryService.viewBenificiary(mobileNumber);
		assertEquals(mobileNumber, benificiary.getMobileNumber());
		assertNotNull(benificiary);
		assertDoesNotThrow(() -> benificiaryService.viewBenificiary(mobileNumber));

		assertThrows(InvalidInputException.class, () -> benificiaryService.viewBenificiary(mobileNumber1));
		assertThrows(InvalidInputException.class, () -> benificiaryService.viewBenificiary(mobileNumber2));
	}


	@Test
	void testViewAllBenificiary() {
		int walletId = 40;
		int walletId1 = 1234;

		List<BenificiaryDetailsDTO> benificiaryDetails = benificiaryService.viewAllBenificiary(walletId);
		assertNotNull(benificiaryDetails);
		assertDoesNotThrow(() -> benificiaryService.viewAllBenificiary(40));

		assertThrows(WalletNotFoundException.class, () -> benificiaryService.viewAllBenificiary(walletId1));
	}
}
