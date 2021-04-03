package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.BillPayment;
import com.capg.payment_wallet_application.dto.BillPaymentDTO;

public class BillPaymentUtil {
	
	public static List<BillPaymentDTO> convertToBillPaymentDtoList(List<BillPayment> list){
		List<BillPaymentDTO> billList = new ArrayList<BillPaymentDTO>();
		
		for(BillPayment bill : list) {
			billList.add(convertToBillPaymentDto(bill));
			
		}
		return billList;
	}
	
	public static BillPayment convertToBillPayment(BillPaymentDTO dto) {
		BillPayment billPayment = new BillPayment();
		
	
		billPayment.setAmount(dto.getAmount());
		billPayment.setBilltype(dto.getBilltype());
		billPayment.setPaymentDate(dto.getPaymentDate());
		billPayment.setWallet(dto.getWallet());
		
		return billPayment;
	}

	public static BillPaymentDTO convertToBillPaymentDto(BillPayment billPayment) {
		BillPaymentDTO dto = new BillPaymentDTO();
		
		dto.setAmount(billPayment.getAmount());
		dto.setBilltype(billPayment.getBilltype());
		dto.setPaymentDate(billPayment.getPaymentDate());
		dto.setWallet(billPayment.getWallet());
		
		return dto;
	}
	
}