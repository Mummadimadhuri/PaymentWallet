package com.capg.payment_wallet_application.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.service.IBenificiaryService;

@RestController
@RequestMapping("/api/pwa/BenifiaciaryDetails")
public class BenificiaryDetailsController {

	@Autowired
	private IBenificiaryService benificiaryservice;

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(value = "/add", consumes = "application/json")
	public BenificiaryDetailsDTO addBenificiary(@RequestBody BenificiaryDetails bd) {
		logger.info("Benificiary details added sucessfully");
		return benificiaryservice.addBenificiary(bd);
	}

	@PutMapping("/updateAccount")
	public BenificiaryDetailsDTO updateBenificiary(@RequestBody BenificiaryDetails bd) {
		logger.info("Benificiary Details Updated sucessfully");
		return benificiaryservice.updateBenificiary(bd);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String>  deleteBenificiary(@RequestBody BenificiaryDetails bd) {
		 logger.info("Benificiary Details deleted sucessfully");
		 String response =benificiaryservice.deleteBenificiary(bd);
		 return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/viewbenificiary/{mobileNo}")
	public BenificiaryDetailsDTO viewBenificiary(@PathVariable String mobileNo) {
		logger.info("Details Of particular benificary is displayed sucessfully");
		return benificiaryservice.viewBenificiary(mobileNo);		
	}

	@GetMapping("/view-all-benificiary/{walletId}")
	public List<BenificiaryDetailsDTO> viewAllBenificiary(@PathVariable int walletId) {
		logger.info("Benificiary of particular wallet is displayed sucessfully");
		return benificiaryservice.viewAllBenificiary(walletId);
	}

}
