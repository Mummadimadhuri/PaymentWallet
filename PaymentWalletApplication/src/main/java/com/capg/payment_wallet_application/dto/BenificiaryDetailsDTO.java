package com.capg.payment_wallet_application.dto;

import org.springframework.stereotype.Component;

@Component
public class BenificiaryDetailsDTO {

	private String name;
	private String mobileNumber;
	private WalletDTO walletDto;

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
		return "BenificiaryDetails [ name=" + name + ", mobileNumber="
				+ mobileNumber + "]";
	}

	public WalletDTO getWalletDto() {
		return walletDto;
	}

	public void setWalletDto(WalletDTO walletDto) {
		this.walletDto = walletDto;
	}
}
