package com.deepak.custom_exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deepak.dtos.Response;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CentralExceptionHandler {
	
	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<?> employeeAlreadyExistsException(EmployeeAlreadyExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PatientAlreadyExistsException.class)
	public ResponseEntity<?> patientAlreadyExistsException(PatientAlreadyExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchUserExistsException.class)
	public ResponseEntity<?> noSuchUserExistsException(NoSuchUserExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchPatientFoundException.class)
	public ResponseEntity<?> patientException(NoSuchPatientFoundException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchEmployeeExistsException.class)
	public ResponseEntity<?> noSuchEmployeeFound(NoSuchEmployeeExistsException e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> allException(Exception e){
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

}
