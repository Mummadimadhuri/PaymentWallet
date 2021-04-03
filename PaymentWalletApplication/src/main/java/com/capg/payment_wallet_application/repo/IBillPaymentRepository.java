package com.capg.payment_wallet_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.beans.Customer;

public interface IBillPaymentRepository extends JpaRepository<BillPayment,Integer> {

	//public BillPayment addBillPayment(BillPayment payment);
	//public BillPayment viewBillPayment(BillPayment payment);
	
	//public BillPayment save(BillPayment payment);
	
	//public BillPayment view(BillPayment payment);
}
