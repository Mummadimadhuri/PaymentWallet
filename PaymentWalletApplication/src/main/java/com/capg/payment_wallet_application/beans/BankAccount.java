package com.capg.payment_wallet_application.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Min(value=100000000000L,message = "Account number must be a 12 digit number")
	private int accountNo;
	
	@Pattern(regexp = "^[A-Z]{4}[0-9]{7}$",
			message = "IFSC code must have 4 alphabets followed by 7 numbers total 11 characters")
	private String ifscCode;
	
	@Pattern(regexp = "^[A-Za-z ]{3,20}$")
	@Size(max=20,message = "Bank name should be less than 30 characters")
	private String bankName;
	
	@DecimalMin(value="1000.0",message = "balance must be a number at least 1000")
	private double balance;
	
	@ManyToOne
	private Wallet wallet;

	public BankAccount() {
		super();
	}

	public BankAccount(@Pattern(regexp = "^[A-Z]{4}[0-9]{7}$") String ifscCode, @Size(max = 20) String bankName,
			@DecimalMin(value = "1000.0", message = "balance must be a number at least 1000") double balance,
			Wallet wallet) {
		super();
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.balance = balance;
		this.wallet = wallet;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", bankName=" + bankName
				+ ", balance=" + balance + ", wallet=" + wallet + "]";
	}
	
	
	
}
