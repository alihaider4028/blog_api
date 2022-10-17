package com.ali.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ali.payload.ApiResponse;

@RestControllerAdvice
public class GlobalController {

@ExceptionHandler(resourceException.class)	
public ResponseEntity<ApiResponse> globalExcpectionHandler(resourceException ex){
	
	return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage(),false),HttpStatus.NOT_FOUND);
	
}
@ExceptionHandler(MethodArgumentNotValidException.class)	
public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
	Map<String,String> resp = new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error)->{
		String field = ((FieldError)error).getField();
		String message= error.getDefaultMessage();
		resp.put(field, message);
	}
			);
	return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	
}


}
