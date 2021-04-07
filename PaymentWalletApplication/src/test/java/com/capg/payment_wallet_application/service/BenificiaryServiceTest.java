package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;

@SpringBootTest
class BenificiaryServiceTest {

	@Autowired
	private BenificiaryService benificiaryService;
	@Test
	void testAddBenificiary() {
		BigDecimal amount = new BigDecimal(10000);
		Wallet wallet = new Wallet(amount);
		BenificiaryDetails benificiary = new BenificiaryDetails("Ramesh", "9344479110");
		wallet.setWalletId(17);
		benificiary.setWallet(wallet);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.addBenificiary(benificiary);
		assertNotNull(benificiaryDetails);
	}

	@Test
	void testUpdateBenificiary() {
		BigDecimal amount = new BigDecimal(10000);
		Wallet wallet = new Wallet(amount);
		BenificiaryDetails benificiary = new BenificiaryDetails("Ganesh", "9789269680");
		wallet.setWalletId(17);
		benificiary.setWallet(wallet);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.updateBenificiary(benificiary);
		assertEquals("Ganesh", benificiaryDetails.getName());
	}

	@Test
	void testDeleteBenificiary() {
		BenificiaryDetails benificiary = new BenificiaryDetails("Ganesh", "9789269680");
		String response = benificiaryService.deleteBenificiary(benificiary);
		assertEquals("Benificiary Details is Deleted", response);
	}

	@Test
	void testViewBenificiary() {
		BenificiaryDetailsDTO benificiary = benificiaryService.viewBenificiary("9842291860");
		assertNotNull(benificiary);
	}

	@Test
	void testViewAllBenificiary() {
		List<BenificiaryDetailsDTO> benificiaryDetails = benificiaryService.viewAllBenificiary(40);
		assertNotNull(benificiaryDetails);
	}
}
