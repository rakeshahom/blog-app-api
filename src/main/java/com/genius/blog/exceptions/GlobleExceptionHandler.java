package com.genius.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.genius.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobleExceptionHandler {

//	in url id mismach error handling......
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String messageString = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(messageString, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

//validation exception handling.......
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> respMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			respMap.put(field, message);
		});
		return new ResponseEntity<Map<String, String>>(respMap, HttpStatus.BAD_REQUEST);
	}


//	in url id mismach error handling......
	@ExceptionHandler(Apiexception.class)
	public ResponseEntity<ApiResponse> handelApiException(Apiexception ex) {
		String messageString = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(messageString, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
}
