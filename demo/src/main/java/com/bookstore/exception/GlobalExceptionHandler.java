package com.bookstore.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex) {
		Map<String,String> errors = new HashMap<String, String>();
		List<FieldError> errs = ex.getBindingResult().getFieldErrors();
		for (FieldError object : errs) {
			errors.put(object.getField(), object.getDefaultMessage());
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleGlobalException(Exception ex) {
		Map<String,String> response = new HashMap<String, String>();
		response.put("error", "Internal Server Error");
		response.put("details", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleRunTimeException(ResourceNotFoundException ex) {
		Map<String,String> response = new HashMap<String, String>();
		response.put("error", "Not Found");
		response.put("details", ex.getMessage());
		response.put("status", "404");
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
