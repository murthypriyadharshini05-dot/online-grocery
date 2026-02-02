package com.grocery.app;

import java.util.Date;
import com.grocery.service.GroceryService;
import com.grocery.util.*;

public class GroceryMain {

    public static void main(String[] args) {

        GroceryService gs = new GroceryService();

        try {
            System.out.println(
                gs.placeNewOrder("CUSG1001",
                new Date(), new Date(),
                "EVENING", 1500)
                ? "ORDER PLACED" : "FAILED");

            System.out.println(
                gs.rescheduleDeliverySlot(750001,
                new Date(), "MORNING")
                ? "RESCHEDULED" : "FAILED");

            System.out.println(
                gs.cancelOrder(750002)
                ? "CANCELLED" : "FAILED");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}








