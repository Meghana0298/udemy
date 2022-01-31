package com.courseservice.java.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorStatus> handleException(CourseNotFoundException ex) {
		ErrorStatus errorStatus = new ErrorStatus();
		errorStatus.setTime(LocalDateTime.now());
		errorStatus.setMessage(ex.getMessage());
		errorStatus.setResponseCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorStatus>(errorStatus, HttpStatus.NOT_FOUND);
	}
	
	
}
