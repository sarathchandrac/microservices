package com.training.mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Mobile Not found")
public class MobileNotFoundException extends RuntimeException {
    private int errorCode;

    public MobileNotFoundException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode=errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
