package com.capg.payment_wallet_application.service;

import java.util.List;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;

public interface IBenificiaryService {

	public BenificiaryDetailsDTO addBenificiary(BenificiaryDetails bd);

	public BenificiaryDetailsDTO updateBenificiary(BenificiaryDetails bd);

	public void deleteBenificiary(BenificiaryDetails bd);

	public BenificiaryDetailsDTO viewBenificiary(BenificiaryDetails bd);

	public List<BenificiaryDetailsDTO> viewAllBenificiary(Customer customer);

}
