package com.capg.payment_wallet_application.dto;

import org.springframework.stereotype.Component;

@Component
public class BenificiaryDetailsDTO {

	private int benificiaryId;
	private String name;
	private String mobileNumber;

	public BenificiaryDetailsDTO() {
		super();
	}

	public BenificiaryDetailsDTO(String name, String mobileNumber) {
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
