package org.workshop.dao;

import org.workshop.models.Customer;
import org.workshop.models.Employee;

import java.sql.*;
import java.util.Arrays;

public class EmployeeDao {
    private static final String CREATE_EMPLOYEE_QUERY =
            "INSERT INTO employees(first_name, last_name, address, phone, note, pay_h) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_EMPLOYEE_QUERY =
            "SELECT id, first_name, last_name, address, phone, note, pay_h FROM employees WHERE id = ?";
    private static final String UPDATE_EMPLOYEE_QUERY =
            "UPDATE employees SET first_name = ?, last_name = ?, address = ?, phone = ?, note = ?, pay_h = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE_QUERY =
            "DELETE FROM employees WHERE id = ?";
    private static final String FIND_ALL_EMPLOYEES_QUERY =
            "SELECT id, first_name, last_name, address, phone, note, pay_h FROM employees";

    public Employee create(Employee employee) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(CREATE_EMPLOYEE_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, employee.getFirst_name());
            ps.setString(2, employee.getLast_name());
            ps.setString(3, employee.getAddress());
            ps.setInt(4, employee.getPhone());
            ps.setString(5, employee.getNote());
            ps.setInt(6, employee.getPay_h());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                employee.setId(id);

                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee read(int employeeId) {
        try (Connection conn = DbUtil.getConn();
             final PreparedStatement ps = conn.prepareStatement(READ_EMPLOYEE_QUERY);
        ) {
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirst_name(rs.getString("first_name"));
                employee.setLast_name(rs.getString("last_name"));
                employee.setAddress(rs.getString("address"));
                employee.setPhone(rs.getInt("phone"));
                employee.setNote(rs.getString("note"));
                employee.setPay_h(rs.getInt("pay_h"));

                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Employee employee) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(UPDATE_EMPLOYEE_QUERY);) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getAddress());
            statement.setInt(4, employee.getPhone());
            statement.setString(5, employee.getNote());
            statement.setInt(6, employee.getPay_h());
            statement.setInt(7, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int employeeId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee[] findAll() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_EMPLOYEES_QUERY);) {
            Employee[] employees = new Employee[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFirst_name(resultSet.getString("first_name"));
                employee.setLast_name(resultSet.getString("last_name"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhone(resultSet.getInt("phone"));
                employee.setPay_h(resultSet.getInt("pay_h"));

                employees = addToArray(employee, employees);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Employee[] addToArray(Employee e, Employee[] employees) {
        Employee[] tmpEmployees = Arrays.copyOf(employees, employees.length + 1);
        tmpEmployees [employees.length] = e;
        return tmpEmployees;
    }
}
