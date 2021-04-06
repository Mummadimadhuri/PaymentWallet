package com.capg.payment_wallet_application.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.dto.BenificiaryDetailsDTO;

@SpringBootTest
class BenificiaryServiceTest {
	
    @Autowired
	BenificiaryService benificiaryService;
	
	@Test
	void testAddBenificiary() {
		 BenificiaryDetails benificiary = new BenificiaryDetails("Ramesh","9344479110");
		 BenificiaryDetailsDTO benificiaryDetails =  benificiaryService.addBenificiary(benificiary);
		 assertNotNull(benificiaryDetails);
	}


	@Test
	void testUpdateBenificiary() {
		 BenificiaryDetails benificiary = new BenificiaryDetails("Ganesh","9789269680");
		 BenificiaryDetailsDTO benificiaryDetails = benificiaryService.updateBenificiary(benificiary);
		 assertEquals("Ganesh",benificiaryDetails.getName());
	}

	@Test
	void testDeleteBenificiary() {
		BenificiaryDetails benificiary = new BenificiaryDetails("Ganesh","9789269680");
		String response = benificiaryService.deleteBenificiary(benificiary); 
		assertEquals("Benificiary Details is Deleted",response);
	}

	@Test
	void testViewBenificiary() {
		BenificiaryDetailsDTO benificiary = benificiaryService.viewBenificiary("9842291860");
		assertNotNull(benificiary);
	}

	//@Test
	//void testViewAllBenificiary() {
		//fail("Not yet implemented");
	//}

}
