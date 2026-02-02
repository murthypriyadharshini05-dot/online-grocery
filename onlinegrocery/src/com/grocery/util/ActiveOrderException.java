package com.grocery.util;

public class ActiveOrderException extends Exception {
    public String toString() {
        return "Customer has active orders";
    }
}

