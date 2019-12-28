package org.workshop.dao;

import org.workshop.models.Employee;
import org.workshop.models.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;

public class OrderDao {
    private static final String CREATE_ORDER_QUERY =
            "INSERT INTO orders(created, plan_start, actual_start, employee_id, description_problem, description_repair, " +
                    "status_id, vehicle_id, price_customer, cost_parts, cost_h, h, end) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_ORDER_QUERY =
            "SELECT id, created, plan_start, actual_start, employee_id, description_problem, description_repair, " +
                    "status_id, vehicle_id, price_customer, cost_parts, cost_h, h, end FROM orders WHERE id = ?";
    private static final String UPDATE_ORDER_QUERY =
            "UPDATE orders SET plan_start = ?, actual_start = ?, employee_id = ?, description_problem = ?, " +
                    "description_repair = ?, status_id = ?, vehicle_id = ?, price_customer = ?, cost_parts = ?, " +
                    "cost_h = ?, h = ?, end = ? WHERE id = ?";
    private static final String DELETE_ORDER_QUERY =
            "DELETE FROM orders WHERE id = ?";
    private static final String FIND_ALL_ORDERS_QUERY =
            "SELECT id, created, plan_start, actual_start, employee_id, description_problem, description_repair," +
                    "status_id, vehicle_id, price_customer, cost_parts, cost_h, h, end FROM orders";
    private static final String FIND_ALL_ORDERS_DESC_QUERY =
            "SELECT id, created, plan_start, actual_start, employee_id, description_problem, description_repair," +
                    "status_id, vehicle_id, price_customer, cost_parts, cost_h, h, end FROM orders ORDER BY created DESC";
    private static final String FIND_ALL_ORDERS_BY_EMPLOYEE_QUERY =
            "SELECT * FROM orders WHERE employee_id = ?;";
    private static final String FIND_ALL_ORDERS_BY_VEHICLE_QUERY =
            "SELECT * FROM orders WHERE vehicle_id = ?;";

    public Order[] findAllDesc() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ORDERS_DESC_QUERY);) {
            Order[] orders = new Order[0];
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated(rs.getDate("created"));
                order.setPlan_start(rs.getDate("plan_start"));
                order.setActual_start(rs.getDate("actual_start"));
                order.setEmployee_id(rs.getInt("employee_id"));
                order.setDescription_problem(rs.getString("description_problem"));
                order.setDescription_repair(rs.getString("description_repair"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setVehicle_id(rs.getInt("vehicle_id"));
                order.setPrice_customer(rs.getInt("price_customer"));
                order.setCost_parts(rs.getInt("cost_parts"));
                order.setCost_h(rs.getInt("cost_h"));
                order.setH(rs.getInt("h"));
                order.setEnd(rs.getDate("end"));

                orders = addToArray(order, orders);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order[] findAllByVehicle(int vehicleId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ORDERS_BY_VEHICLE_QUERY);) {
            Order[] orders = new Order[0];
            statement.setInt(1, vehicleId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated(rs.getDate("created"));
                order.setPlan_start(rs.getDate("plan_start"));
                order.setActual_start(rs.getDate("actual_start"));
                order.setEmployee_id(rs.getInt("employee_id"));
                order.setDescription_problem(rs.getString("description_problem"));
                order.setDescription_repair(rs.getString("description_repair"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setVehicle_id(rs.getInt("vehicle_id"));
                order.setPrice_customer(rs.getInt("price_customer"));
                order.setCost_parts(rs.getInt("cost_parts"));
                order.setCost_h(rs.getInt("cost_h"));
                order.setH(rs.getInt("h"));
                order.setEnd(rs.getDate("end"));

                orders = addToArray(order, orders);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order[] findAllByEmployee(int employeeId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ORDERS_BY_EMPLOYEE_QUERY);) {
            Order[] orders = new Order[0];
            statement.setInt(1, employeeId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated(rs.getDate("created"));
                order.setPlan_start(rs.getDate("plan_start"));
                order.setActual_start(rs.getDate("actual_start"));
                order.setEmployee_id(rs.getInt("employee_id"));
                order.setDescription_problem(rs.getString("description_problem"));
                order.setDescription_repair(rs.getString("description_repair"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setVehicle_id(rs.getInt("vehicle_id"));
                order.setPrice_customer(rs.getInt("price_customer"));
                order.setCost_parts(rs.getInt("cost_parts"));
                order.setCost_h(rs.getInt("cost_h"));
                order.setH(rs.getInt("h"));
                order.setEnd(rs.getDate("end"));

                orders = addToArray(order, orders);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order create(Order order) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            EmployeeDao edao = new EmployeeDao();
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setDate(2, order.getPlan_start());
            ps.setDate(3, order.getActual_start());
            ps.setInt(4, order.getEmployee_id());
            ps.setString(5, order.getDescription_problem());
            ps.setString(6, order.getDescription_repair());
            ps.setInt(7, 1);
            ps.setInt(8, order.getVehicle_id());
            ps.setInt(9, order.getPrice_customer());
            ps.setInt(10, order.getCost_parts());
            ps.setInt(11, edao.read(order.getEmployee_id()).getPay_h());
            ps.setInt(12, order.getH());
            ps.setDate(13, order.getEnd());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                order.setId(id);

                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order read(int orderId) {
        try (Connection conn = DbUtil.getConn();
             final PreparedStatement ps = conn.prepareStatement(READ_ORDER_QUERY);
        ) {
            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated(rs.getDate("created"));
                order.setPlan_start(rs.getDate("plan_start"));
                order.setActual_start(rs.getDate("actual_start"));
                order.setEmployee_id(rs.getInt("employee_id"));
                order.setDescription_problem(rs.getString("description_problem"));
                order.setDescription_repair(rs.getString("description_repair"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setVehicle_id(rs.getInt("vehicle_id"));
                order.setPrice_customer(rs.getInt("price_customer"));
                order.setCost_parts(rs.getInt("cost_parts"));
                order.setCost_h(rs.getInt("cost_h"));
                order.setH(rs.getInt("h"));
                order.setEnd(rs.getDate("end"));

                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Order order) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(UPDATE_ORDER_QUERY);) {

            statement.setDate(1, order.getPlan_start());
            statement.setDate(2, order.getActual_start());
            statement.setInt(3, order.getEmployee_id());
            statement.setString(4, order.getDescription_problem());
            statement.setString(5, order.getDescription_repair());
            statement.setInt(6, order.getStatus_id());
            statement.setInt(7, order.getVehicle_id());
            statement.setInt(8, order.getPrice_customer());
            statement.setInt(9, order.getCost_parts());
            statement.setInt(10, order.getCost_h());
            statement.setInt(11, order.getH());
            statement.setDate(12, order.getEnd());
            statement.setInt(13, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int orderId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(DELETE_ORDER_QUERY)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order[] findAll() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ORDERS_QUERY);) {
            Order[] orders = new Order[0];
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setCreated(rs.getDate("created"));
                order.setPlan_start(rs.getDate("plan_start"));
                order.setActual_start(rs.getDate("actual_start"));
                order.setEmployee_id(rs.getInt("employee_id"));
                order.setDescription_problem(rs.getString("description_problem"));
                order.setDescription_repair(rs.getString("description_repair"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setVehicle_id(rs.getInt("vehicle_id"));
                order.setPrice_customer(rs.getInt("price_customer"));
                order.setCost_parts(rs.getInt("cost_parts"));
                order.setCost_h(rs.getInt("cost_h"));
                order.setH(rs.getInt("h"));
                order.setEnd(rs.getDate("end"));

                orders = addToArray(order, orders);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Order[] addToArray(Order o, Order[] orders) {
        Order[] tmpOrders = Arrays.copyOf(orders, orders.length + 1);
        tmpOrders [orders.length] = o;
        return tmpOrders;
    }
}
