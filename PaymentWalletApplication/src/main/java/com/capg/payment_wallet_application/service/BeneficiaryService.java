package com.capg.payment_wallet_application.service;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.BeneficiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.BeneficiaryDetailsDTO;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.repo.IBeneficiaryRepository;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.BeneficiaryDetailsUtils;

@Service
public class BeneficiaryService implements IBeneficiaryService {

	@Autowired
	private IBeneficiaryRepository beneficiaryRepo;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WalletRepo walletRepo;

	@Override
	public BeneficiaryDetailsDTO addBeneficiary(BeneficiaryDetails beneficiaryDetails) {
		logger.info("addBeneficiary() is get intiated");
		BeneficiaryDetails beneficiarydetails = beneficiaryRepo.save(beneficiaryDetails);
		logger.info("addBeneficiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBeneficiaryDetailsDto(beneficiarydetails);
	}

	@Override
	public BeneficiaryDetailsDTO updateBeneficiary(BeneficiaryDetails beneficiaryDetails) {
		logger.info("updateBeneficiary() is get intiated");
		BeneficiaryDetails beneficiarydetails = beneficiaryRepo.save(beneficiaryDetails);
		logger.info("updateBeneficiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBeneficiaryDetailsDto(beneficiarydetails);
	}

	@Override
	public String deleteBeneficiary(BeneficiaryDetails beneficiaryDetails) {
		logger.info("deleteBenificiary() is get intiated");
		if (beneficiaryRepo.findById(beneficiaryDetails.getMobileNo()) != null) {
			beneficiaryRepo.delete(beneficiaryDetails);
			logger.info("deleteBeneficiary() is get executed");
			return "Beneficiary Details is Deleted";
		} else {
			throw new InvalidInputException("Beneficiary is not present in the data base");
		}
	}

	@Override
	public BeneficiaryDetailsDTO viewBeneficiary(String mobileNo) {
		logger.info("viewBeneficiary() is get intiated");
		if (!mobileNoValidation(mobileNo)) {
			throw new InvalidInputException("Mobile number should be a 10 digit number with first digit from 6 to 9");
		}
		BeneficiaryDetails beneficiarydetails = beneficiaryRepo.findById(mobileNo).orElse(null);
		if (beneficiarydetails == null) {
			throw new InvalidInputException("Mobile no is not registered to any beneficiary");
		}
		logger.info("viewBenificiary() is get executed");
		return BeneficiaryDetailsUtils.convertToBeneficiaryDetailsDto(beneficiarydetails);
	}

	@Override
	public List<BeneficiaryDetailsDTO> viewAllBeneficiary(int walletId) {
		Customer wallet = walletRepo.findByWalletId(walletId);
		if (wallet != null) {
			logger.info("viewAllBenificiary() is get intiated");
			List<BeneficiaryDetails> list = beneficiaryRepo.viewAllBeneficiary(walletId);
			logger.info("viewAllBenificiary() is get executed");
			return BeneficiaryDetailsUtils.convertToBeneficiaryDetailsDtoList(list);
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