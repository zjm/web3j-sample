package com.ethjava.utils;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (message) : s;
    }

}