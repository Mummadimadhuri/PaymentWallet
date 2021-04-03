package com.capg.payment_wallet_application.dto;

import org.springframework.stereotype.Component;

import com.capg.payment_wallet_application.beans.Wallet;

@Component
public class CustomerDTO {

	private String name;
	private String mobileNo;
	private String password;
	private Wallet wallet;

	public CustomerDTO() {
	}

	public CustomerDTO( String name2,String mobileNo2, Wallet wallet2) {
		this.name = name2;
		mobileNo = mobileNo2;
		// wallet=wallet2;
	}

	public CustomerDTO(String name,String mobileNo) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.wallet = new Wallet(); 
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Customer name=" + name + ", mobileNo=" + mobileNo+wallet;
	}

}
