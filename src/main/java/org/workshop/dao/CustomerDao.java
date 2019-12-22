package org.workshop.dao;

import org.workshop.models.Customer;

import java.sql.*;
import java.util.Arrays;

public class CustomerDao {
    private static final String CREATE_CUSTOMER_QUERY =
            "INSERT INTO customers(first_name, last_name, dob) VALUES (?, ?, ?)";
    private static final String READ_CUSTOMER_QUERY =
            "SELECT id, first_name, last_name, dob FROM customers WHERE id = ?";
    private static final String UPDATE_CUSTOMER_QUERY =
            "UPDATE customers SET first_name = ?, last_name = ?, dob = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY =
            "DELETE FROM customers WHERE id = ?";
    private static final String FIND_ALL_CUSTOMERS_QUERY =
            "SELECT id, first_name, last_name, dob FROM customers";

    public Customer create(Customer customer) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(CREATE_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, customer.getFirst_name());
            ps.setString(2, customer.getLast_name());
            ps.setDate(3, customer.getDob());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                customer.setId(id);

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Customer read(int customerId) {
        try (Connection conn = DbUtil.getConn();
             final PreparedStatement ps = conn.prepareStatement(READ_CUSTOMER_QUERY);
        ) {
            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setDob(rs.getDate("dob"));

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Customer customer) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(UPDATE_CUSTOMER_QUERY);) {

            statement.setString(1, customer.getFirst_name());
            statement.setString(2, customer.getLast_name());
            statement.setDate(3, customer.getDob());
            statement.setInt(4, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int customerId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(DELETE_CUSTOMER_QUERY)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer[] findAll() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_CUSTOMERS_QUERY);) {
            Customer[] customers = new Customer[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setFirst_name(resultSet.getString("first_name"));
                customer.setLast_name(resultSet.getString("last_name"));
                customer.setDob(resultSet.getDate("dob"));

                customers = addToArray(customer, customers);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Customer[] addToArray(Customer c, Customer[] customers) {
        Customer[] tmpCustomers = Arrays.copyOf(customers, customers.length + 1);
        tmpCustomers [customers.length] = c;
        return tmpCustomers;
    }
}
