package com.capg.payment_wallet_application.repo;

import com.capg.payment_wallet_application.beans.BillPayment;

public interface IBillPaymentRepository {

	public BillPayment addBillPayment(BillPayment payment);
	public BillPayment viewBillPayment(BillPayment payment);
}
