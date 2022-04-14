package com.ecram.usersmicroecram.exceptions;

public class UserNotFoundedException extends GeneralException {
    public UserNotFoundedException(String message, String errorCode) {
        super(message, errorCode);
    }
}
