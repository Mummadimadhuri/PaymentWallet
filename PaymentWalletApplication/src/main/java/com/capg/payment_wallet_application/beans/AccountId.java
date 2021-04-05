package com.capg.payment_wallet_application.beans;

import java.io.Serializable;

public class AccountId implements Serializable{

	private static final long serialVersionUID = 1L;

	private int accountNo;

	private String ifscCode;

	public AccountId() {
		
	}
	
	public AccountId(int accountNo, String ifscCode) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
}
