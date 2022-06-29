package com.example.handsOnProject2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StudentAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExistsException(StudentAlreadyExistsException exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails("Student with this name already exists",request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
