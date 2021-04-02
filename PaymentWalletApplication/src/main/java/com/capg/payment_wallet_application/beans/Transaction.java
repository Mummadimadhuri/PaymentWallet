package com.capg.payment_wallet_application.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionId;
	
	private String transactionType;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private String transactionDate;
	
	@ManyToOne
	public Wallet wallet;
	
	@DecimalMin(value="1.0", message = "amount should be at least 1.0")
	private double amount;
	
	@Size(max = 100)
	private String description;

	public Transaction() {
		super();
	}

	public Transaction(int transactionId, String transactionType, String transactionDate, Wallet wallet, double amount,
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

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
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
