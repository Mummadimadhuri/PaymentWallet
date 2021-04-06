package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.IBillPaymentRepository;
import com.capg.payment_wallet_application.util.BillPaymentUtil;

@Service
public class BillPaymentServiceImpl implements IBillPaymentService {

	@Autowired
	private IBillPaymentRepository billRepo;

	@Override
	public BillPaymentDTO addBillPayment(BillPayment payment) {
		BigDecimal currentBalance = payment.getWallet().getBalance();
		BigDecimal amount = (BigDecimal.valueOf(payment.getAmount()));
		if (amount.compareTo(currentBalance) < 0) {
			currentBalance = currentBalance.subtract(amount);
			payment.getWallet().setBalance(currentBalance);
			return BillPaymentUtil.convertToBillPaymentDto(billRepo.save(payment));
		} else {
			throw new InsufficientBalanceException("Balance of wallet is not Sufficient to do Transaction");
		}
	}

	@Override
	public BillPaymentDTO viewBillPayment(int billId) {

		BillPayment payment = billRepo.findById(billId).orElse(null);
		if (payment == null) {
			throw new InvalidInputException("Wrong Credentials");
		} 
	     return BillPaymentUtil.convertToBillPaymentDto(payment);
		}
}
