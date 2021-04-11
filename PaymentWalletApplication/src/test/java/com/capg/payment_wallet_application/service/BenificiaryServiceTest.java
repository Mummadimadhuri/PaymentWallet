package com.capg.payment_wallet_application.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.TransactionSystemException;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.util.WalletUtils;

@SpringBootTest
class BenificiaryServiceTest {

	@Autowired
	private BenificiaryService benificiaryService;
	
	@Autowired
	private WalletServiceImpl walletService;
	
	@Test
	void testAddBenificiary() {
		CustomerDTO customer = walletService.createAccount("Mummadi madhuri", "9440207619", new BigDecimal(1000), "Madhuri@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		String name = "Ravi";
		String name1 = "Suresh";
		String name2 ="Sathish2";
		String mobileNumber = "9875623456";
		String mobileNumber2 = "9445891860566";

		BenificiaryDetails benificiary = new BenificiaryDetails(name, mobileNumber);
		benificiary.setWallet(wallet);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.addBenificiary(benificiary);
		assertNotNull(benificiaryDetails);
		assertEquals(mobileNumber, benificiaryDetails.getMobileNumber());
		assertDoesNotThrow(() -> benificiaryService.addBenificiary(benificiary));

		BenificiaryDetails benificiary2 = new BenificiaryDetails(name1, mobileNumber2);
		benificiary2.setWallet(wallet);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.addBenificiary(benificiary2));
		BenificiaryDetails benificiary3 = new BenificiaryDetails(name2, mobileNumber);
		benificiary3.setWallet(wallet);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.addBenificiary(benificiary3));
	}

	@Test
	void testUpdateBenificiary() {
		CustomerDTO customer = walletService.createAccount("Mummadi Madhuri","9440207615",new BigDecimal(1000),"Madhuri@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		
		 String name = "Ravi",name1="Suresh12";
		 String mobileNumber ="9875623456",mobileNumber2="987574754a";
		 
		BenificiaryDetails benificiary = new BenificiaryDetails(name,mobileNumber);
		benificiary.setWallet(wallet);
		BenificiaryDetailsDTO benificiaryDetails = benificiaryService.updateBenificiary(benificiary);
		assertEquals(name, benificiaryDetails.getName());
		assertNotNull(benificiary);
		assertDoesNotThrow(()->benificiaryService.updateBenificiary(benificiary));
		
		BenificiaryDetails benificiary1 = new BenificiaryDetails(name1,mobileNumber);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.updateBenificiary(benificiary1));
		BenificiaryDetails benificiary2 = new BenificiaryDetails(name,mobileNumber2);
		assertThrows(TransactionSystemException.class, () -> benificiaryService.updateBenificiary(benificiary2));
	}

	@Test
	void testDeleteBenificiary() {
		CustomerDTO customer = walletService.createAccount("Mummadi Madhuri","9440207603",new BigDecimal(1000),"Madhuri@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		
		String name = "Ravi",name1="Suresh3";
		String mobileNumber = "9875623456",mobileNumber2="987574754a";

		BenificiaryDetails benificiary = new BenificiaryDetails(name,mobileNumber);
		benificiary.setWallet(wallet);
		String response = benificiaryService.deleteBenificiary(benificiary);
		assertEquals("Benificiary Details is Deleted", response);

		BenificiaryDetails benificiary2 = new BenificiaryDetails();
		assertThrows(InvalidDataAccessApiUsageException.class,
				() -> benificiaryService.deleteBenificiary(benificiary2));
	}

	@Test
	void testViewBenificiary() {
		CustomerDTO customer = walletService.createAccount("Mummadi Madhuri","9440207605",new BigDecimal(1000),"Madhuri@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		BenificiaryDetails beneficiary = new BenificiaryDetails("Deepan","9360043198");
		beneficiary.setWallet(wallet);
		BenificiaryDetailsDTO beneficiary1 = benificiaryService.addBenificiary(beneficiary);
		String mobileNumber = beneficiary1.getMobileNumber();
		BenificiaryDetailsDTO beneficiary2 = benificiaryService.viewBenificiary(mobileNumber); 
		assertNotNull(beneficiary2);
		assertEquals(beneficiary1.getMobileNumber(),beneficiary2.getMobileNumber());
		
		String mobileNumber1 = "9842291860";
		String mobileNumber2="984229186a";
		
		assertThrows(InvalidInputException.class,()->benificiaryService.viewBenificiary(mobileNumber1));
		assertThrows(InvalidInputException.class,()->benificiaryService.viewBenificiary(mobileNumber2));
	}


	@Test
	void testViewAllBenificiary() {
		CustomerDTO customer = walletService.createAccount("Mummadi Madhuri","9440207561",new BigDecimal(1000),"Madhuri@2000");
		Wallet wallet = WalletUtils.convertToWallet(customer.getWalletDto());
		BenificiaryDetails beneficiary = new BenificiaryDetails("Maran","7854356789");
		beneficiary.setWallet(wallet);
		BenificiaryDetails beneficiary1 = new BenificiaryDetails("Mara","7854356788");
		beneficiary1.setWallet(wallet);
		BenificiaryDetailsDTO beneficiary2 =benificiaryService.addBenificiary(beneficiary);
		BenificiaryDetailsDTO beneficiary3 =benificiaryService.addBenificiary(beneficiary1);
		int walletId = beneficiary2.getWalletDto().getWalletId();
		int walletId1 =1000;
		List<BenificiaryDetailsDTO> benificiaryDetails = benificiaryService.viewAllBenificiary(walletId);
		assertNotNull(benificiaryDetails);
		assertThrows(WalletNotFoundException.class,()->benificiaryService.viewAllBenificiary(walletId1));
	}
}
