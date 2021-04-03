package com.capg.payment_wallet_application.Controller;

import java.util.List;

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
import com.capg.payment_wallet_application.service.BenificiaryService;

@RestController
@RequestMapping("/BenifiaciaryDetails")
public class BenificiaryDetailsController {
     
	@Autowired
	private BenificiaryService benificiaryservice;
	
	
	@PostMapping(value = "/add", consumes = "application/json")
	public BenificiaryDetailsDTO addBenificiary(@RequestBody BenificiaryDetails bd)
	{
		return benificiaryservice.addBenificiary(bd) ;
	}
	
	@PutMapping("/updateAccount")
	public BenificiaryDetailsDTO updateBenificiary(@RequestBody BenificiaryDetails bd)
	{
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
		return benificiaryservice.viewBenificiary(bd);		
	}
	
	@GetMapping("/view-all-benificiary")
	public List<BenificiaryDetailsDTO> viewAllBenificiary(@RequestBody Customer customer)
	{
		return benificiaryservice.viewAllBenificiary(customer);
	}
	
}
