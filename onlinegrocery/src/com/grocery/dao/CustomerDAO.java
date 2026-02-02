package com.grocery.dao;

import java.sql.*;
import java.util.*;
import com.grocery.bean.Customer;
import com.grocery.util.DBUtil;

public class CustomerDAO {

    public Customer findCustomer(String customerID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM CUSTOMER_TBL WHERE CUSTOMER_ID=?");
        ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Customer c = new Customer();
            c.setCustomerID(rs.getString(1));
            c.setFullName(rs.getString(2));
            c.setMobile(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setPreferredSlot(rs.getString(5));
            return c;
        }
        return null;
    }

    public List<Customer> viewAllCustomers() throws Exception {
        List<Customer> list = new ArrayList<>();
        Connection con = DBUtil.getDBConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMER_TBL");

        while (rs.next()) {
            Customer c = new Customer();
            c.setCustomerID(rs.getString(1));
            c.setFullName(rs.getString(2));
            c.setMobile(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setPreferredSlot(rs.getString(5));
            list.add(c);
        }
        return list;
    }

    public boolean insertCustomer(Customer c) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO CUSTOMER_TBL VALUES(?,?,?,?,?)");
        ps.setString(1, c.getCustomerID());
        ps.setString(2, c.getFullName());
        ps.setString(3, c.getMobile());
        ps.setString(4, c.getAddress());
        ps.setString(5, c.getPreferredSlot());
        int r = ps.executeUpdate();
        con.commit();
        return r > 0;
    }

    public boolean deleteCustomer(String customerID) throws Exception {
        Connection con = DBUtil.getDBConnection();
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM CUSTOMER_TBL WHERE CUSTOMER_ID=?");
        ps.setString(1, customerID);
        int r = ps.executeUpdate();
        con.commit();
        return r > 0;
    }
}





