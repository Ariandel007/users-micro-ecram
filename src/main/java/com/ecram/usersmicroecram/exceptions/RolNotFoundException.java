package com.ecram.usersmicroecram.exceptions;

public class RolNotFoundException extends GeneralException {
    public RolNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}