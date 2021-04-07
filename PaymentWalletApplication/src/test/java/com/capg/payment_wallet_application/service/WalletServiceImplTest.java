package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.CustomerDTO;

@SpringBootTest
class WalletServiceImplTest {
	
	@Autowired
	WalletServiceImpl walletService;

	@Test
	void testCreateAccount() {
		BigDecimal amount = new BigDecimal(10000);
		CustomerDTO createAccount = walletService.createAccount("ArunKumar","9344567801",amount,"Arun@2000");
		assertNotNull(createAccount);
	}

	@Test
	void testShowBalance() {
		CustomerDTO balance = walletService.showBalance("9842291860");
		assertNotNull(balance);
	}

	@Test
	void testFundTransfer() {
		BigDecimal amount = new BigDecimal(100);
		CustomerDTO balance =walletService.fundTransfer("9789269680","9842291860",amount);
		assertNotNull(balance);
	}

	@Test
	void testDepositAmount() {
		BigDecimal amount = new BigDecimal(10000);
		CustomerDTO balance =walletService.depositAmount("9842291860",amount);
		assertNotNull(balance);
	}

	@Test
	void testWithdrawAmount() {
		BigDecimal amount = new BigDecimal(5000);
		CustomerDTO balance =walletService.withdrawAmount("9842291860",amount);
		assertNotNull(balance);
	}

	@Test
	void testGetList() {
		List<CustomerDTO> list = walletService.getList();
		assertNotNull(list);
	}

	@Test
	void testUpdateAccount() {
		Customer customer = new Customer("Arun","9344567801","Arun@2000");
		CustomerDTO update =walletService.updateAccount(customer);
		assertNotNull(update);
	}

	@Test
	void testAddMoney() {
		BigDecimal amount = new BigDecimal(20000);
		Wallet wallet = new Wallet(amount);
		wallet.setWalletId(40);
		CustomerDTO addMoney = walletService.addMoney(wallet,1000);
	    assertNotNull(addMoney);
	}

}
