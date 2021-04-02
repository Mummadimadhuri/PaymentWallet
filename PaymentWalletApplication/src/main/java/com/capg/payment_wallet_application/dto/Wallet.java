package com.capg.payment_wallet_application.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class Wallet {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(value=1000)
	private int walletId;
	
	@DecimalMin(value="Balance must be at least 1000.0")
	private BigDecimal balance;

	@Autowired
	public Wallet() {

	}

	public Wallet(BigDecimal amount) {
		this.balance = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return " balance= " + balance;
	}

}

