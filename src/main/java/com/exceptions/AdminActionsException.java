package com.exceptions;

public class AdminActionsException extends RuntimeException {

    public AdminActionsException(String errorMsg) {
        super(errorMsg);
    }
}
