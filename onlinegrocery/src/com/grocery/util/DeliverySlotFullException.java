package com.grocery.util;

public class DeliverySlotFullException extends Exception {
    public String toString() {
        return "Delivery slot capacity full";
    }
}

