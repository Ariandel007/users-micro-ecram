package com.ecram.usersmicroecram.exceptions;

public class GeneralException extends RuntimeException {
    private String errorCode;
    public GeneralException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
