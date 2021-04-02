package com.capg.payment_wallet_application.service;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;

public interface IBenificiaryService {

	public BenificiaryDetails addBenificiary(BenificiaryDetails bd);
	public BenificiaryDetails updateBenificiary(BenificiaryDetails bd);
	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd);
	public BenificiaryDetails viewBenificiary(BenificiaryDetails bd);
	public BenificiaryDetails viewAllBenificiary(Customer customer);
	
}
