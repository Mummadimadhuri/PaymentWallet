package com.capg.payment_wallet_application.dto;

<<<<<<< HEAD
import java.time.LocalDate;

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
=======
import java.sql.Date;
>>>>>>> branch 'master' of https://github.com/Mummadimadhuri/PaymentWallet
import org.springframework.stereotype.Component;
import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class TransactionDTO {
	
	private int transactionId;
	private String transactionType;
<<<<<<< HEAD
	
	@Column(name = "transactiondate") 
	@DateTimeFormat(pattern = "dd-mm-yyyy")
//	@Temporal(TemporalType.DATE)
	private LocalDate transactionDate;
	
	@ManyToOne
=======
	private Date transactionDate;
>>>>>>> branch 'master' of https://github.com/Mummadimadhuri/PaymentWallet
	public Wallet wallet;
	private double amount;
	private String description;

	public TransactionDTO() {
		super();
	}

<<<<<<< HEAD
	public TransactionDTO(int transactionId, String transactionType, LocalDate transactionDate, Wallet wallet, double amount,
			@Size(max = 100) String description) {
=======
	public TransactionDTO(int transactionId, String transactionType, Date transactionDate, Wallet wallet, double amount, String description) {
>>>>>>> branch 'master' of https://github.com/Mummadimadhuri/PaymentWallet
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






