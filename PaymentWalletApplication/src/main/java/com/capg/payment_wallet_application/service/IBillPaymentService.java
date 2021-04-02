package com.capg.payment_wallet_application.service;

import com.capg.payment_wallet_application.beans.BillPayment;

public interface IBillPaymentService {

	public BillPayment addBillPayment(BillPayment payment);
	public BillPayment viewBillPayment(BillPayment payment);
}
