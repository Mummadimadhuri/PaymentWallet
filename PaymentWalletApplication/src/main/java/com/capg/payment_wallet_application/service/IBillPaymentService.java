package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;

public interface IBillPaymentService {

	public BillPaymentDTO addBillPayment(BillPayment payment);
	public List<BillPaymentDTO> viewBillPayment(BillPayment payment);
}
