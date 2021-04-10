package com.capg.payment_wallet_application.service;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.repo.IBenificiaryRepository;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.BeneficiaryDetailsUtils;

@Service
public class BenificiaryService implements IBenificiaryService {

	@Autowired
	IBenificiaryRepository ibenificiaryrepo;

	@Autowired
	WalletRepo walletRepo;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public BenificiaryDetailsDTO addBenificiary(BenificiaryDetails bd) {
		logger.info("addBenificiary() is get intiated");
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save(bd);
		logger.info("addBenificiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public BenificiaryDetailsDTO updateBenificiary(BenificiaryDetails bd) {
		logger.info("updateBenificiary() is get intiated");
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.save(bd);
		logger.info("updateBenificiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public String deleteBenificiary(BenificiaryDetails bd) {
		logger.info("deleteBenificiary() is get intiated");
		if (ibenificiaryrepo.findById(bd.getMobileNumber()) != null) {
			ibenificiaryrepo.delete(bd);
			logger.info("deleteBenificiary() is get executed");
			return "Benificiary Details is Deleted";
		} else {
			throw new InvalidInputException("Benificiary is not present in the data base");
		}
	}

	@Override
	public BenificiaryDetailsDTO viewBenificiary(String mobileNo) {
		logger.info("viewBenificiary() is get intiated");
		if (!mobileNoValidation(mobileNo)) {
			throw new InvalidInputException("Mobile number should be a 10 digit number with first digit from 6 to 9");
		}
		BenificiaryDetails benificiarydetails = ibenificiaryrepo.findById(mobileNo).orElse(null);
		if (benificiarydetails == null) {
			throw new InvalidInputException("Mobile no is not registered to any benificiary");
		}
		logger.info("viewBenificiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDto(benificiarydetails);
	}

	@Override
	public List<BenificiaryDetailsDTO> viewAllBenificiary(int walletId) {
		Customer wallet = walletRepo.findByWalletId(walletId);
		if (wallet != null) {
			logger.info("viewAllBenificiary() is get intiated");
			List<BenificiaryDetails> list = ibenificiaryrepo.viewAllBenificiary(walletId);
			logger.info("viewAllBenificiary() is get executed");
			return BeneficiaryDetailsUtils.convertToBenificiaryDetailsDtoList(list);
		} else {
			throw new WalletNotFoundException("The Given wallet is not Found");
		}
	}

	private static boolean mobileNoValidation(String mobileNo) {
		boolean flag = false;
		if (Pattern.matches("^[6-9][0-9]{9}$", mobileNo)) {
			flag = true;
		}
		return flag;
	}

}
