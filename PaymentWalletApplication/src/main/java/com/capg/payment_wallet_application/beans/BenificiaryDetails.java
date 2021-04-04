package com.capg.payment_wallet_application.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class BenificiaryDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int benificiaryId;
	
	@Pattern(regexp="^[A-Za-z ]{3,30}$",message = "Name should be in the range of 3 to 30 characters")
	private String name;
	
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$",
			message = "Mobile number should have exactly 10 digits and start with a number 6 to 9")
	private String mobileNumber;

	public BenificiaryDetails() {
		
		super();
	}

	public BenificiaryDetails(@Pattern(regexp = "^[A-Za-z ]{3,30}$", message = "Name should be in the range of 3 to 30 characters") String name,
			@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile number should have exactly 10 digits and start with a number 6 to 9") String mobileNumber) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "BenificiaryDetails [benificiaryId=" + benificiaryId + ", name=" + name + ", mobileNumber="
				+ mobileNumber + "]";
	}
}
