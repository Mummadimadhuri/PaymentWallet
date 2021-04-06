package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;
import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;

public class BeneficiaryDetailsUtils {

	private BeneficiaryDetailsUtils() {

	}

	public static List<BenificiaryDetailsDTO> convertToBenificiaryDetailsDtoList(List<BenificiaryDetails> list) {
		List<BenificiaryDetailsDTO> dtolist = new ArrayList<>();
		for (BenificiaryDetails BenificiaryDetails : list)
			dtolist.add(convertToBenificiaryDetailsDto(BenificiaryDetails));
		return dtolist;
	}

	public static BenificiaryDetails convertToBenificiaryDetails(BenificiaryDetailsDTO dto) {
		BenificiaryDetails benificiarydetails = new BenificiaryDetails();

		benificiarydetails.setMobileNumber(dto.getMobileNumber());
		benificiarydetails.setName(dto.getName());
		benificiarydetails.setWallet(WalletUtils.convertToWallet(dto.getWalletDto()));
		return benificiarydetails;

	}

	public static BenificiaryDetailsDTO convertToBenificiaryDetailsDto(BenificiaryDetails benificiarydetails) {
		BenificiaryDetailsDTO benificiarydetailsDTO = new BenificiaryDetailsDTO();

		benificiarydetailsDTO.setMobileNumber(benificiarydetails.getMobileNumber());
		benificiarydetailsDTO.setName(benificiarydetails.getName());
		benificiarydetailsDTO.setWalletDto(WalletUtils.convertToWalletDto(benificiarydetails.getWallet()));
		return benificiarydetailsDTO;

	}
}
