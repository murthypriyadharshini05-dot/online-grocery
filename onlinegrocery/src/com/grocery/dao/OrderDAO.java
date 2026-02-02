package com.grocery.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.grocery.bean.GroceryOrder;
import com.grocery.util.DBUtil;

public class OrderDAO {

    public int generateOrderID() throws Exception {
        Connection con = DBUtil.getDBConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT NVL(MAX(ORDER_ID),750000)+1 FROM ORDER_TBL");
        rs.next();
        return rs.getInt(1);
    }

    public boolean recordOrder(GroceryOrder o) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO ORDER_TBL VALUES(?,?,?,?,?,?,?)");
        ps.setInt(1, o.getOrderID());
        ps.setString(2, o.getCustomerID());
        ps.setDate(3, new java.sql.Date(o.getOrderDate().getTime()));
        ps.setDate(4, new java.sql.Date(o.getDeliveryDate().getTime()));
        ps.setString(5, o.getDeliverySlot());
        ps.setDouble(6, o.getOrderAmount());
        ps.setString(7, o.getStatus());
        int r = ps.executeUpdate();
        con.commit();
        return r > 0;
    }

    public boolean updateOrderStatus(int orderID, String status) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE ORDER_TBL SET STATUS=? WHERE ORDER_ID=?");
        ps.setString(1, status);
        ps.setInt(2, orderID);
        int r = ps.executeUpdate();
        con.commit();
        return r > 0;
    }

    public boolean updateOrderDelivery(int orderID, java.util.Date d, String slot) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE ORDER_TBL SET DELIVERY_DATE=?, DELIVERY_SLOT=? WHERE ORDER_ID=?");
        ps.setDate(1, new java.sql.Date(d.getTime()));
        ps.setString(2, slot);
        ps.setInt(3, orderID);
        int r = ps.executeUpdate();
        con.commit();
        return r > 0;
    }

    public List<GroceryOrder> findOrdersByCustomer(String customerID) throws Exception {
        List<GroceryOrder> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM ORDER_TBL WHERE CUSTOMER_ID=?");
        ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            GroceryOrder o = new GroceryOrder();
            o.setOrderID(rs.getInt(1));
            o.setCustomerID(rs.getString(2));
            o.setOrderDate(rs.getDate(3));
            o.setDeliveryDate(rs.getDate(4));
            o.setDeliverySlot(rs.getString(5));
            o.setOrderAmount(rs.getDouble(6));
            o.setStatus(rs.getString(7));
            list.add(o);
        }
        return list;
    }

    public List<GroceryOrder> findOrdersByDeliverySlot(java.util.Date dd, String slot) throws Exception {
        List<GroceryOrder> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM ORDER_TBL WHERE DELIVERY_DATE=? AND DELIVERY_SLOT=?");
        ps.setDate(1, new java.sql.Date(dd.getTime()));
        ps.setString(2, slot);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            GroceryOrder o = new GroceryOrder();
            o.setOrderID(rs.getInt(1));
            o.setStatus(rs.getString(7));
            list.add(o);
        }
        return list;
    }
}





