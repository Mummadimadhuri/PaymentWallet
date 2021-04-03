package com.capg.payment_wallet_application.util;

import java.util.ArrayList;
import java.util.List;

import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.dto.CustomerDTO;


public class CustomerUtils {
	public static List<CustomerDTO> convertToCustomerDtoList(List<Customer> list) {
		List<CustomerDTO> dtolist = new ArrayList<CustomerDTO>();
		for (Customer Customer : list)
			dtolist.add(convertToCustomerDto(Customer));
		return dtolist;
	}
	public static Customer convertToCustomer(CustomerDTO dto) {
		Customer customer = new Customer();
	
			customer.setMobileNo(dto.getMobileNo());
			customer.setName(dto.getName());
			customer.setWallet(dto.getWallet());
			return customer;
		
	}
	public static CustomerDTO convertToCustomerDto(Customer customer)
	{
		CustomerDTO customerdto = new CustomerDTO();
		customerdto.setMobileNo(customer.getMobileNo());
		customerdto.setName(customer.getMobileNo());
	    customerdto.setWallet(customer.getWallet());
		return customerdto;
  }
}
