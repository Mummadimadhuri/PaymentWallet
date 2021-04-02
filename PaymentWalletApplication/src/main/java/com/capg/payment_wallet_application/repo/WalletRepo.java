package com.capg.payment_wallet_application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Customer,Integer> {

	public Customer save(Customer customer);
	
	@Query("select c from Customer c where c.mobileNo = :mobileNo")
	public Customer findOne(String mobileNo);
	
	@Query("select c from Customer c")
	public List<Customer> getList();
	
	
	public Customer findByWallet(Wallet wallet);
	
	public Customer findByMobileNo(String sourceMobileNo);
}
