package com.capg.payment_wallet_application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.service.IBillPaymentService;

@RestController
@RequestMapping("/bill-payment")
public class BillPaymentController {
	
	@Autowired
	IBillPaymentService billService;
	
	@PostMapping(value="/add_bill",consumes ="application/json")
	public BillPayment addBillPayment(@RequestBody BillPayment payment) {
		return billService.addBillPayment(payment);
	}

	
	@GetMapping(value="/view", produces = "application/json")
	public List<BillPayment> viewBillPayment(BillPayment payment) {
		return billService.viewBillPayment(payment);
	}
}
