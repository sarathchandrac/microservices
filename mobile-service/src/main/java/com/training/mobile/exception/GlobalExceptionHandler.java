package com.training.mobile.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value = MobileNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleMobileNotFoundException(MobileNotFoundException mfe){
        ErrorDetails error = ErrorDetails
        						.builder()
        						.errorCode(mfe.getErrorCode())
        						.errorMessage(mfe.getMessage())
        						.build();
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDetails>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	List<ErrorDetails> errorDetails = ex.getBindingResult().getAllErrors()
													    		.stream()
													    		.map(error -> ErrorDetails
													    				.builder()
													    				.errorCode(10001)
													    				.errorMessage(error.getDefaultMessage())
													    				.build())
													    				.collect(Collectors.toList());
    	return ResponseEntity.badRequest().body(errorDetails);
    }
/*
    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ErrorDetails> handleThrowable(Throwable throwable){
        ErrorDetails error = new ErrorDetails();
        error.setErrorCode(1009);
        error.setErrorMessage(throwable.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
*/

}
