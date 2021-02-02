package com.example.orderplanning.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(final String msg) {
        super(msg);
    }
}
