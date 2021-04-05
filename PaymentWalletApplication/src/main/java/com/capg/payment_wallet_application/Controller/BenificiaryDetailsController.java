package com.capg.payment_wallet_application.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;
import com.capg.payment_wallet_application.service.IBenificiaryService;

@RestController
@RequestMapping("/BenifiaciaryDetails")
public class BenificiaryDetailsController {
     
	@Autowired
	private IBenificiaryService benificiaryservice;
	
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping(value = "/add", consumes = "application/json")
	public BenificiaryDetailsDTO addBenificiary(@RequestBody BenificiaryDetails bd)
	{
		LOGGER.info("Beneficiary added succesfully");
		return benificiaryservice.addBenificiary(bd) ;
	}
	
	@PutMapping("/updateAccount")
	public BenificiaryDetailsDTO updateBenificiary(@RequestBody BenificiaryDetails bd)
	{
		LOGGER.info("Beneficiary updated succesfully");
		return benificiaryservice.updateBenificiary(bd);
	}
	
	@DeleteMapping("/delete")
	public void deleteBenificiary(@RequestBody BenificiaryDetails bd)
	{
		return;
	}
	
	@GetMapping("/viewbenificiary")
	public BenificiaryDetailsDTO viewBenificiary(@RequestBody BenificiaryDetails bd) 
	{
		LOGGER.info("Beneficiary are listed below");
		return benificiaryservice.viewBenificiary(bd);		
	}
	
	@GetMapping("/view-all-benificiary")
	public List<BenificiaryDetailsDTO> viewAllBenificiary(@RequestBody Customer customer)
	{
		LOGGER.info("Beneficiary are listed below");
		return benificiaryservice.viewAllBenificiary(customer);
	}
	
}
