package com.grocery.service;

import java.util.*;
import com.grocery.bean.*;
import com.grocery.dao.*;
import com.grocery.util.*;

public class GroceryService {

    CustomerDAO customerDAO = new CustomerDAO();
    OrderDAO orderDAO = new OrderDAO();
    private static final int MAX_SLOT = 20;

    public boolean placeNewOrder(String cid, Date od, Date dd,
                                 String slot, double amt)
            throws Exception {

        if (cid == null || slot == null || amt <= 0 || dd.before(od))
            throw new ValidationException();

        if (customerDAO.findCustomer(cid) == null)
            return false;

        if (orderDAO.findOrdersByDeliverySlot(dd, slot).size() >= MAX_SLOT)
            throw new DeliverySlotFullException();

        GroceryOrder o = new GroceryOrder();
        o.setOrderID(orderDAO.generateOrderID());
        o.setCustomerID(cid);
        o.setOrderDate(od);
        o.setDeliveryDate(dd);
        o.setDeliverySlot(slot);
        o.setOrderAmount(amt);
        o.setStatus("SCHEDULED");

        return orderDAO.recordOrder(o);
    }

    public boolean cancelOrder(int orderID) throws Exception {
        if (orderID <= 0) throw new ValidationException();
        return orderDAO.updateOrderStatus(orderID, "CANCELLED");
    }

    public boolean rescheduleDeliverySlot(int orderID, Date d, String slot)
            throws Exception {

        if (orderID <= 0 || slot == null)
            throw new ValidationException();

        if (orderDAO.findOrdersByDeliverySlot(d, slot).size() >= MAX_SLOT)
            throw new DeliverySlotFullException();

        return orderDAO.updateOrderDelivery(orderID, d, slot);
    }
}


