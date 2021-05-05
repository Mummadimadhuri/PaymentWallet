package com.capg.payment_wallet_application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.payment_wallet_application.beans.AccountId;
import com.capg.payment_wallet_application.beans.BankAccount;
import com.capg.payment_wallet_application.beans.BeneficiaryDetails;
import com.capg.payment_wallet_application.beans.Customer;
import com.capg.payment_wallet_application.beans.Transaction;
import com.capg.payment_wallet_application.beans.Wallet;
import com.capg.payment_wallet_application.dto.CustomerDTO;
import com.capg.payment_wallet_application.dto.TransactionDTO;
import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;
import com.capg.payment_wallet_application.exception.WalletNotFoundException;
import com.capg.payment_wallet_application.repo.IAccountRepository;
import com.capg.payment_wallet_application.repo.IBeneficiaryRepository;
import com.capg.payment_wallet_application.repo.ITransactionRepository;
import com.capg.payment_wallet_application.repo.WalletRepo;
import com.capg.payment_wallet_application.util.CustomerUtils;
import com.capg.payment_wallet_application.util.TransactionUtils;

/*
 * Implemented Service Name : Wallet Service
 * Author                   : Arun Kumar M
 * Implementation Start Date: 2021-04-05
 * implementation End Date  : 2021-04-06
 * Used Annotation          : @Service,@Autowired,@Override
 * Validation               : Validation are done at Required Place
 * */
@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletRepo walletRepo;

	@Autowired
	private ITransactionRepository transactionRepo;

	@Autowired
	private IBeneficiaryRepository benificiaryRepo;

	@Autowired
	private IAccountRepository accountRepo;

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	String invalidMobileNo = "Mobile number should be a 10 digit number with first digit from 6 to 9";
	String unregisteredMobileNo = "Mobile number is not registered to any customer";

	/*
	 * Description : A new customer is created with a new wallet 
	 * Input Param :String, String, BigDecimal, String 
	 * Return Value : CustomerDTO 
	 * Exception :InvalidInputException, InsufficientBalanceException,
	 * 				ConstraintViolationException, TransactionSystemException
	 */
	@Override
	public CustomerDTO createAccount(Customer customer) {
		logger.info("createAccount() is get intiated");
		if (walletRepo.findOne(customer.getMobileNo()) != null) {
			throw new InvalidInputException("This mobileno has already been registered to a customer");
		}
		if (customer.getWallet().getBalance().compareTo(new BigDecimal(1)) <= 0) {
			throw new InsufficientBalanceException("amount should be atleast 1.0");
		}
		if (!validatePassword(customer.getPassword())) {
			throw new InvalidInputException("Password should contain at least one Capital letter, "
					+ "one small letter, one number and one special character");
		}
		String password = Integer.valueOf(customer.getPassword().hashCode()).toString();
		customer.setPassword(password);
		logger.info("createAccount() is get executed");
		return CustomerUtils.convertToCustomerDto(walletRepo.save(customer));
	}

	/*
	 * Description : Returns the customer object with its wallet balance 
	 * Input Param: String Return Value : CustomerDTO 
	 * Exception : InvalidInputException
	 */
	@Override
	public CustomerDTO showBalance(String mobileno) {
		logger.info("showBalance() is get intiated");
		Customer customer = null;
		if (mobileNoValidation(mobileno)) {
			customer = walletRepo.findOne(mobileno);
		} else {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (customer == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		logger.info("showBalance() is get executed");
		return CustomerUtils.convertToCustomerDto(customer);
	}

	/*
	 * Description : Transfers money from one customer to another using the mobile
	 * 				number of two customers Also adds the transaction object for both 
	 * 				sender and receiver customers Also adds a benificiary object of 
	 * 				the receiver for sender
	 * Input Param : String, String, BigDecimal 
	 * Return Value : CustomerDTO 
	 * Exceptionc: InvalidInputException, InsufficientBalanceException
	 */
	@Override
	public TransactionDTO fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		logger.info("fundTransfer() is get intiated");
		if (!mobileNoValidation(sourceMobileNo)) {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (!mobileNoValidation(targetMobileNo)) {
			throw new InvalidInputException(invalidMobileNo);
		}
		if (sourceMobileNo.equals(targetMobileNo)) {
			throw new InvalidInputException("Sender and receiver should not be same");
		}
		if (amount.compareTo(new BigDecimal(1)) <= 0) {
			throw new InsufficientBalanceException("amount should be atleast 1.0");
		}
		Customer source = walletRepo.findById(sourceMobileNo).orElse(null);
		Customer target = walletRepo.findById(targetMobileNo).orElse(null);
		if (source == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		if (target == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		Wallet sourceWallet = source.getWallet();
		Wallet targetWallet = target.getWallet();
		if (sourceWallet.getBalance().compareTo(amount) <= 0) {
			throw new InsufficientBalanceException("Source doesn't have enough balance");
		}
		sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
		targetWallet.setBalance(targetWallet.getBalance().add(amount));
		Transaction sourceTransaction = new Transaction("SEND", LocalDate.now(), sourceWallet,
				Double.parseDouble(amount.toString()), "Sending " + amount + " to person " + targetMobileNo);
		Transaction targetTransaction = new Transaction("RECEIVE", LocalDate.now(), targetWallet,
				Double.parseDouble(amount.toString()), "Receiving " + amount + " from person" + sourceMobileNo);
		BeneficiaryDetails beneficiary = new BeneficiaryDetails(target.getName(), target.getMobileNo());
		beneficiary.setWallet(sourceWallet);
		benificiaryRepo.save(beneficiary);
		transactionRepo.save(sourceTransaction);
		transactionRepo.save(targetTransaction);
		walletRepo.save(source);
		walletRepo.save(target);
		logger.info("showBalance() is get executed");
		return TransactionUtils.convertToTransactionDto(sourceTransaction);
	}

	/*
	 * Description : Adds amount to the wallet of the customer 
	 * Input Param : String,BigDecimal 
	 * Return Value : CustomerDTO Exception : InvalidInputException
	 */
	@Override
	public TransactionDTO depositAmount(int walletId, BigDecimal amount) {
		Customer customer = null;
		logger.info("depositAmount() is get intiated");
		if (amount.compareTo(new BigDecimal(1)) <= 0) {
			throw new InvalidInputException("amount should be atleast 1.0");
		}
		customer = walletRepo.findByWalletId(walletId);
		if (customer == null) {
			throw new WalletNotFoundException("Invalid Wallet Id");
		}
		customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		Wallet wallet = customer.getWallet();
		walletRepo.save(customer);
		Transaction transaction = new Transaction("RECEIVE",LocalDate.now(),wallet,Double.parseDouble(amount.toString()),"Receiving "+amount+" from deposit");
		logger.info("depositAmount() is get executed");
		return TransactionUtils.convertToTransactionDto(transaction);
	}

	/*
	 * Description : Withdraws money from the wallet 
	 * Input Param : String,BigDecimal 
	 * Return Value : CustomerDTO 
	 * Exception : InvalidInputException, InsufficientBalanceException
	 */
	@Override
	public TransactionDTO withdrawAmount(int walletId, BigDecimal amount) {
		logger.info("withdrawAmount() is get intiated()");
		Customer customer = null;
		if (amount.compareTo(new BigDecimal(1)) <= 0) {
			throw new InvalidInputException("amount should be atleast 1.0");
		}
		customer = walletRepo.findByWalletId(walletId);
		if (customer == null) {
			throw new InvalidInputException(unregisteredMobileNo);
		}
		BigDecimal currentBalance = customer.getWallet().getBalance();
		if (currentBalance.compareTo(amount) >= 0) {
			currentBalance = currentBalance.subtract(amount);
		} else {
			throw new InsufficientBalanceException(currentBalance.toString() + " is only available, You are "
					+ amount.subtract(currentBalance) + " short.");
		}
		customer.getWallet().setBalance(currentBalance);
		Wallet wallet = customer.getWallet();
		walletRepo.save(customer);
		Transaction transaction = new Transaction("SEND",LocalDate.now(),wallet,Double.parseDouble(amount.toString()),"Receiving "+amount+" from deposit");
		logger.info("withdrawAmount() is get executed");
		return TransactionUtils.convertToTransactionDto(transaction);
	}

	/*
	 * Description : Gives the list of all the customers 
	 * Input Param : - 
	 * Return Value : List<CustomerDTO> 
	 * Exception : -
	 */
	@Override
	public List<CustomerDTO> getList() {
//		logger.info("list of customer is intiated");
		List<Customer> list = walletRepo.getList();
//		logger.info("list of customer is get executed");
		return CustomerUtils.convertToCustomerDtoList(list);
	}

	/*
	 * Description : Updates the customer details 
	 * Input Param : Customer 
	 * Return Value : CustomerDTO 
	 * Exception : InvalidInputException, InsufficientBalanceException, 
	 * ConstraintViolationException, TransactionSystemException
	 */
	@Override
	public CustomerDTO updateAccount(Customer customer) {
		logger.info("updateAccount() is get intiated");
		logger.info(customer.toString());
		if (customer.getWallet().getBalance().compareTo(new BigDecimal(1)) <= 0) {
			throw new InsufficientBalanceException("amount should be atleast 1.0");
		}
		if(customer.getPassword()!=null) {
			if (!validatePassword(customer.getPassword())) {
				throw new InvalidInputException("Password should contain at least one Capital letter, "
						+ "one small letter, one number and one special character");
			}
			customer.setPassword(Integer.valueOf(customer.getPassword().hashCode()).toString());
		}
		if (!mobileNoValidation(customer.getMobileNo())) {
			throw new InvalidInputException("Mobile number should be a 10 digit number with first digit from 6 to 9");
		}
		if (walletRepo.findOne(customer.getMobileNo()) == null) {
			throw new InvalidInputException("The customer with given mobile no doesn't exist");
		}
		Customer dataCustomer = walletRepo.findOne(customer.getMobileNo());
		if(customer.getPassword()==null) {
			customer.setPassword(dataCustomer.getPassword());
		}
		walletRepo.save(customer);
		logger.info("updateAccount() is get executed");
		return CustomerUtils.convertToCustomerDto(customer);
	}

	/*
	 * Description : Adds money to wallet from a customer's bank account 
	 * Input Param: int, double 
	 * Return Value : CustomerDTO 
	 * Exception : InvalidInputException, InsufficientBalanceException
	 */
	@Override
	public TransactionDTO addMoney(BankAccount account, int walletId, double amount) {
		logger.info("addMoney() is get intiated");
		if (amount < 1) {
			throw new InvalidInputException("amount should be atleast 1.0");
		}
		Customer customer = walletRepo.findByWalletId(walletId);
		Wallet wallet = customer.getWallet();
		if (account.getBalance() <= amount) {
			throw new InsufficientBalanceException("Your account doesn't have enough money");
		}
		account.setBalance(account.getBalance() - amount);
		accountRepo.save(account);
		wallet.setBalance(wallet.getBalance().add(BigDecimal.valueOf(amount)));
		customer.setWallet(wallet);
		walletRepo.save(customer);
		Transaction transaction = new Transaction("RECEIVE",LocalDate.now(),wallet,amount,"Added money to wallet "+amount+" from bank "+account.getBankName()+" "+account.getAccountNo());
		transactionRepo.save(transaction);
		logger.info("addMoney() is get executed");
		return TransactionUtils.convertToTransactionDto(transaction);
	}

	/*
	 * Description : Validates the mobile number based on given standards 
	 * Input Param : String
	 * Return Value : boolean Exception : -
	 */
	private static boolean mobileNoValidation(String mobileNo) {
		boolean flag = false;
		if (Pattern.matches("^[6-9][0-9]{9}$", mobileNo)) {
			flag = true;
		}
		return flag;
	}

	/*
	 * Description : Validates the password based on the standards 
	 * Input Param : String 
	 * Return Value : boolean 
	 * Exception : -
	 */
	private static boolean validatePassword(String password) {
		boolean flag = false;
		if (Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()[{}]:;',?/*~$^+=<>]).{8,20}$", password)) {
			flag = true;
		}
		return flag;
	}
}
