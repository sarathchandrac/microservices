package com.training.mobile.exception;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.training.mobile.dto.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value = MobileNotFoundException.class)
    public ResponseEntity<Response> handleMobileNotFoundException(MobileNotFoundException mfe){
        ErrorDetails error = ErrorDetails
        						.builder()
        						.errorCode(mfe.getErrorCode())
        						.errorMessage(mfe.getMessage())
        						.build();
        Response response = Response.builder().errors(Arrays.asList(error)).build();
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	List<ErrorDetails> errorDetails = ex.getBindingResult().getAllErrors()
													    		.stream()
													    		.map(error -> ErrorDetails
													    				.builder()
													    				.errorCode(10001)
													    				.errorMessage(error.getDefaultMessage())
													    				.build())
													    				.collect(Collectors.toList());
    	 Response response = Response.builder().errors(errorDetails).build();
    	 return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Response> handleThrowable(Throwable throwable){
        ErrorDetails error = ErrorDetails.builder()
        						.errorCode(1009)
        						.errorMessage(throwable.getMessage()).build();
        Response response = Response.builder().errors(Arrays.asList(error)).build();
   	 	return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}
