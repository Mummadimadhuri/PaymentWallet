package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;
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
		currentBalance = currentBalance.subtract(amount);
		payment.getWallet().setBalance(currentBalance);
		return BillPaymentUtil.convertToBillPaymentDto(billRepo.save(payment));
	}
	@Override
	public List<BillPaymentDTO> viewBillPayment(BillPayment payment) {
		
		return  BillPaymentUtil.convertToBillPaymentDtoList(billRepo.findAll());
	}

}
