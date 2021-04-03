package com.capg.payment_wallet_application.Controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.capg.payment_wallet_application.exception.InsufficientBalanceException;
import com.capg.payment_wallet_application.exception.InvalidInputException;

@ControllerAdvice
public class Exceptions {

	@ExceptionHandler(value=ConstraintViolationException.class)
	public ResponseEntity<Object> exceptionConstraintViolationException(ConstraintViolationException exception){
		StringBuilder msg = new StringBuilder();
		exception.getConstraintViolations().forEach(i->msg.append(i.getConstraintDescriptor().getMessageTemplate()));
		return new ResponseEntity<>(msg.toString(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(value=InsufficientBalanceException.class)
	public ResponseEntity<Object> exceptionInsufficientBalanceException(InsufficientBalanceException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=InvalidInputException.class)
	public ResponseEntity<Object> exceptionInvalidInputException(InvalidInputException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value=MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> exceptionMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
		return new ResponseEntity<>("Invalid Date Format",HttpStatus.NOT_ACCEPTABLE);
	}
}
