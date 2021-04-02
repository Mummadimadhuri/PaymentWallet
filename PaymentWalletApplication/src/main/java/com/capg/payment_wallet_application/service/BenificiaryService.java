package com.capg.payment_wallet_application.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.repo.IBenificiaryRepository;

public class BenificiaryService implements IBenificiaryService {
      
	@Autowired
	IBenificiaryRepository ibenificiaryrepo ;

	@Override
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd) {
		// TODO Auto-generated method stub
		return ibenificiaryrepo.addBenificiary(bd);
	}

	@Override
	public BenificiaryDetails updateBenificiary(BenificiaryDetails bd) {
		// TODO Auto-generated method stub
		return ibenificiaryrepo.updateBenificiary( bd);
	}

	@Override
	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd) {
		// TODO Auto-generated method stub
		return ibenificiaryrepo.deleteBenificiary(bd);
	}

	@Override
	public BenificiaryDetails viewBenificiary(BenificiaryDetails bd) {
		// TODO Auto-generated method stub
		return ibenificiaryrepo.viewBenificiary(bd);
	}

	@Override
	public BenificiaryDetails viewAllBenificiary(Customer customer) {
		// TODO Auto-generated method stub
		return ibenificiaryrepo.viewAllBenificiary(customer);
	}

}
