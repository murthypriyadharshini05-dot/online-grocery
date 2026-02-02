package com.grocery.main;

import java.sql.Date;
import java.util.Scanner;

import com.grocery.service.GroceryService;
import com.grocery.util.DeliverySlotFullException;
import com.grocery.util.ValidationException;

public class GroceryMain {
private static GroceryService groceryService;
public static void main(String[] args) {
groceryService = new GroceryService();
Scanner sc = new Scanner(System.in);
System.out.println(" Online Grocery Order Console ");
// TEST 1: Place a new order
try {
boolean r = groceryService.placeNewOrder("CUSG1001",
new Date(0), new Date(0), "EVENING", 1450.00);
System.out.println(r ? "ORDER PLACED" : "ORDER FAILED");
} catch (DeliverySlotFullException e) {
System.out.println("Slot Error: " + e.toString());
} catch (ValidationException e) {
System.out.println("Validation Error: " +
e.toString());
} catch (Exception e) {
System.out.println("System Error: " +
e.getMessage());
}
// TEST 2: Reschedule an existing order
try {
boolean r =
groceryService.rescheduleDeliverySlot(750001, new Date(0), "MORNING");
System.out.println(r ? "RESCHEDULED" : "RESCHEDULE FAILED");
} catch (DeliverySlotFullException e) {
System.out.println("Slot Error: " + e.toString());
} catch (ValidationException e) {
System.out.println("Validation Error: " +
e.toString());
} catch (Exception e) {
System.out.println("System Error: " +
e.getMessage());
}
// TEST 3: Cancel an order
try {
boolean r = groceryService.cancelOrder(750002);
System.out.println(r ? "CANCELLED" : "CANCEL FAILED");
} catch (ValidationException e) {
System.out.println("Validation Error: " +
e.toString());
} catch (Exception e) {
System.out.println("System Error: " +
e.getMessage());
}
sc.close();
}
}
