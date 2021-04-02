package com.capg.payment_wallet_application.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component

public class Customer {

	@Pattern(regexp = "^[A-Za-z ]{3,20}$",
			message = "Name must only be alphabets and whitespaces from 3 to 20 characters")
	private String name;
	
	@Id
	@NotNull
	@Column(unique = true)
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}")
	private String mobileNo;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])(?={8,})")
	private String password;
	
	@Autowired
	@OneToOne
	private Wallet wallet;

	public Customer() {
	}

	public Customer(@Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Name must only be alphabets and whitespaces from 3 to 20 characters") String name2,
			@Pattern(regexp = "^[6-9]{1}[0-9]{9}") String mobileNo2, Wallet wallet2) {
		this.name = name2;
		mobileNo = mobileNo2;
		// wallet=wallet2;
	}

	public Customer(
			@Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Name must only be alphabets and whitespaces from 3 to 20 characters") String name,
			@Pattern(regexp = "^[6-9]{1}[0-9]{9}") String mobileNo) {
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
