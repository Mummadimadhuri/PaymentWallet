package com.capg.payment_wallet_application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;

public interface IBenificiaryRepository extends JpaRepository<BenificiaryDetails,Integer> {

    @Query("select bd from BenificiaryDetails bd where bd = :bd ")
	public BenificiaryDetails viewBenificiary(@Param(value="bd") BenificiaryDetails bd);
    @Query("select bd from Benificiarydetails bd where bd.wallet = (select c.wallet from customer c)")
	public List<BenificiaryDetails> viewAllBenificiary(Customer customer);
	
}
