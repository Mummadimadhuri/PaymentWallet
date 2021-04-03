package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BillPayment;

public interface IBillPaymentService {

	public BillPayment addBillPayment(BillPayment payment);
	public List<BillPayment> viewBillPayment(BillPayment payment);
}
