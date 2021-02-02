package com.example.orderplanning.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(final String msg) {
        super(msg);
    }
}
