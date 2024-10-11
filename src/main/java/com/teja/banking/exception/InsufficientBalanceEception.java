package com.teja.banking.exception;

public class InsufficientBalanceEception extends RuntimeException{
    public InsufficientBalanceEception(String message) {
        super(message);
    }
}
