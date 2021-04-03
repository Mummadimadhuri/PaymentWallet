package com.capg.payment_wallet_application.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class TransactionDTO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	
	private String transactionType;
	
	@Column(name = "transactiondate") 
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@ManyToOne
	public Wallet wallet;
	
	@DecimalMin(value="1.0", message = "amount should be at least 1.0")
	private double amount;
	
	@Size(max = 100)
	private String description;

	public TransactionDTO() {
		super();
	}

	public TransactionDTO(int transactionId, String transactionType, Date transactionDate, Wallet wallet, double amount,
			@Size(max = 100) String description) {
		super();
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.wallet = wallet;
		this.amount = amount;
		this.description = description;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
