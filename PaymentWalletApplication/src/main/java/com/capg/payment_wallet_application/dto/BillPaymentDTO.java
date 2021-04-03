package com.capg.payment_wallet_application.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.capg.payment_wallet_application.beans.BillType;
import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class BillPaymentDTO {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(value=1000)
	private int billId;
	
	@ManyToOne
	private Wallet wallet;
	private BillType billtype;
	
	@DecimalMin(value = "1.0", message="amount should be at least 1.0")
	private double amount;
	
	@DateTimeFormat(pattern="dd/mm/yyyy")
	private LocalDate paymentDate;
	
	public BillPaymentDTO() {
		
	}
	
	public BillPaymentDTO(int billId, Wallet wallet, BillType billtype, @DecimalMin("1000.0") double amount, LocalDate paymentDate) {
		super();
		this.billId = billId;
		this.wallet = wallet;
		this.billtype = billtype;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public BillType getBilltype() {
		return billtype;
	}
	public void setBilltype(BillType billtype) {
		this.billtype = billtype;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}


	@Override
	public String toString() {
		return "BillPaymentDTO [billId=" + billId + ", wallet=" + wallet + ", billtype=" + billtype + ", amount="
				+ amount + ", paymentDate=" + paymentDate + "]";
	}
	
	

}
