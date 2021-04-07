package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.beans.BillType;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;

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
		BillPayment payment = new BillPayment(wallet,BillType.DTH,10000,date);
		BillPaymentDTO billPayment = billServiceImpl.addBillPayment(payment);
	    assertNotNull(billPayment);
	}

	@Test
	void testViewBillPayment() {
		BillPaymentDTO billPayment = billServiceImpl.viewBillPayment(104);
		LocalDate expected = LocalDate.parse("2020-07-01");
		assertEquals(expected,billPayment.getPaymentDate());
	}
}
