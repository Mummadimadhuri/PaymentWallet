package com.capg.payment_wallet_application.dto;



import org.springframework.stereotype.Component;

import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class BankAccountDTO {
	
	private int accountNo;
	private String ifscCode;
	private String bankName;
	private double balance;
	private Wallet wallet;

	public BankAccountDTO() {
		super();
	}

	public BankAccountDTO( String ifscCode,String bankName, double balance,Wallet wallet) {
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
