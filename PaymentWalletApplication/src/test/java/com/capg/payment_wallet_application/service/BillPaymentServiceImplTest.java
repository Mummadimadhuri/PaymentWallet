package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.beans.BillType;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;

@SpringBootTest
class BillPaymentServiceImplTest {
	
	@Autowired
	private BillPaymentServiceImpl billServiceImpl;
	
    @Test
	void testAddBillPayment() {
		BigDecimal balance = new BigDecimal(20000);
		Wallet wallet = new Wallet(balance);
		wallet.setWalletId(40);
		LocalDate date = LocalDate.parse("2000-03-11");
		double amount = 10000;
        double amount1 =25000;
		
		BillPayment payment = new BillPayment(wallet,BillType.DTH,amount,date);
		BillPaymentDTO billPayment = billServiceImpl.addBillPayment(payment);
	    assertNotNull(billPayment);
	    assertEquals(amount,payment.getAmount());
	    
	    BillPayment payment1 = new BillPayment(wallet,BillType.DTH,amount1,date);
	    assertThrows(InsufficientBalanceException.class,()-> billServiceImpl.addBillPayment(payment1));
	    
	}

	@Test
	void testViewBillPayment() {
		int billId = 104;
		int billId1 = 1000;
		BillPaymentDTO billPayment = billServiceImpl.viewBillPayment(billId);
		LocalDate expected = LocalDate.parse("2020-07-01");
		assertEquals(expected,billPayment.getPaymentDate());
		assertNotNull(billPayment);
		Assertions.assertDoesNotThrow(()->billServiceImpl.viewBillPayment(billId));
		
		assertThrows(InvalidInputException.class,()->billServiceImpl.viewBillPayment(billId1));
		
	}
}
