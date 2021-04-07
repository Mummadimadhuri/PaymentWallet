package com.capg.payment_wallet_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.repo.IBenificiaryRepository;
import com.capg.payment_wallet_application.util.BeneficiaryDetailsUtils;

@Service
public class BenificiaryService implements IBenificiaryService {

	@Autowired
	IBenificiaryRepository ibenificiaryrepo;

	@Override
	public BenificiaryDetailsDTO addBenificiary(BenificiaryDetails bd) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save(bd);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public BenificiaryDetailsDTO updateBenificiary(BenificiaryDetails bd) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save(bd);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public String deleteBenificiary(BenificiaryDetails bd) {
		if(ibenificiaryrepo.findById(bd.getMobileNumber())!=null) {
			ibenificiaryrepo.delete(bd);
			return "Benificiary Details is Deleted";
		} else {
			throw new InvalidInputException("Benificiary is not present in the data base");
		}
	}

	@Override
	public BenificiaryDetailsDTO viewBenificiary(String mobileNo) {
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.findById(mobileNo).orElse(null);
		if(benificiarydetails==null) {
			throw new InvalidInputException("Mobile no is not registered to any benificiary");
		}
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public List<BenificiaryDetailsDTO> viewAllBenificiary(int walletId) {
		List<BenificiaryDetails> list = ibenificiaryrepo.viewAllBenificiary(walletId);
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDtoList(list);
	}

}
