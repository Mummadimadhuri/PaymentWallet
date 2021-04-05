package com.capg.payment_wallet_application.dto;


import java.time.LocalDate;



import org.springframework.stereotype.Component;
import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class TransactionDTO {
	
	private int transactionId;
	private String transactionType;

	private LocalDate transactionDate;
	
	private Wallet wallet;
	private double amount;
	private String description;

	public TransactionDTO() {
		super();
	}


	public TransactionDTO(int transactionId, String transactionType, LocalDate transactionDate, Wallet wallet, double amount,
			String description) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.wallet = wallet;
		this.amount = amount;
		this.description = description;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate localDate) {
		this.transactionDate = localDate;
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






