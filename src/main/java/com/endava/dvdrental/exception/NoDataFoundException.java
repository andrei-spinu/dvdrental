package com.endava.dvdrental.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
        super("No data found");
    }
}
