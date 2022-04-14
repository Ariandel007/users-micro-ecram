package com.ecram.usersmicroecram.exceptions;

public class UserDuplicateException extends GeneralException {
    public UserDuplicateException(String message, String errorCode) {
        super(message, errorCode);
    }
}
