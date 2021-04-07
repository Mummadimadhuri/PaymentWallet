package com.capg.payment_wallet_application.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.payment_wallet_application.beans.BenificiaryDetails;

@Repository
public interface IBenificiaryRepository extends JpaRepository<BenificiaryDetails,String> {

    @Query("select bd from BenificiaryDetails bd where bd = :bd ")
	public BenificiaryDetails viewBenificiary(@Param(value="bd") BenificiaryDetails bd);

//    @Query("select bacc from BankAccount bacc where bacc.wallet.walletId=:walletId")
    @Query("select bd from BenificiaryDetails bd where bd.wallet.walletId=:walletId")
	public List<BenificiaryDetails> viewAllBenificiary(@Param("walletId") int walletId);
	
    public Optional<BenificiaryDetails> findById(String mobileNumber);
}
