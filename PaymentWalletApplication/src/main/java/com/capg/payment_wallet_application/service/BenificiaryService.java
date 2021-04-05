package com.capg.payment_wallet_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.IBenificiaryRepository;
import com.capg.payment_wallet_application.util.BeneficiaryDetailsUtils;

@Service
public class BenificiaryService implements IBenificiaryService {
	
	@Autowired
	IBenificiaryRepository ibenificiaryrepo ;

	@Override
	public BenificiaryDetailsDTO addBenificiary(BenificiaryDetails bd) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save( bd);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public BenificiaryDetailsDTO updateBenificiary(BenificiaryDetails bd) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save( bd);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public void deleteBenificiary(BenificiaryDetails bd) {
		if(ibenificiaryrepo.findByMobileNumber(bd.getMobileNumber())!=null) {
			ibenificiaryrepo.delete(bd);
		}
		else {
			throw new InvalidInputException("Benificiary is not present in the data base");
		}
	}

	@Override
	public BenificiaryDetailsDTO viewBenificiary(BenificiaryDetails bd) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.viewBenificiary(bd);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public List<BenificiaryDetailsDTO> viewAllBenificiary(Customer customer) {
		List<BenificiaryDetails> list  = ibenificiaryrepo.viewAllBenificiary(customer);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDtoList(list);
	}

}
