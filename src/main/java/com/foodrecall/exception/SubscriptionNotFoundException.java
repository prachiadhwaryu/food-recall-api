package com.foodrecall.exception;

public class SubscriptionNotFoundException extends RuntimeException {

    public SubscriptionNotFoundException(Long id) {
        super("No subscription found with Id : " + id);
    }

}
