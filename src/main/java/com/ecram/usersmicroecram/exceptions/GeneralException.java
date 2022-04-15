package com.ecram.usersmicroecram.exceptions;

public class GeneralException extends Exception {
    private String errorCode;
    public GeneralException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
