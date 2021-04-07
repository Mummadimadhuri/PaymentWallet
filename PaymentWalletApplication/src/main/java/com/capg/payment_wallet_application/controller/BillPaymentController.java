package com.capg.payment_wallet_application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;
import com.capg.payment_wallet_application.service.IBillPaymentService;

@RestController
@RequestMapping("/api/pwa/bill-payment")
public class BillPaymentController {

	@Autowired
	private IBillPaymentService billService;
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PutMapping(value = "/add-bill", consumes = "application/json")
	public BillPaymentDTO addBillPayment(@RequestBody BillPayment payment) {
		logger.info("BillPayment is added sucessfully");
		return billService.addBillPayment(payment);
	}

	@GetMapping(value = "/view", produces = "application/json")
	public BillPaymentDTO viewBillPayment(int billId) {
		logger.info("BillPayment is displayed sucessfully through billId");
		return billService.viewBillPayment(billId);
	}
}
