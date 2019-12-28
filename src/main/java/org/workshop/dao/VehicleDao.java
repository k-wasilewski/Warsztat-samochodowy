package org.workshop.dao;

import org.workshop.models.Customer;
import org.workshop.models.Employee;
import org.workshop.models.Vehicle;

import java.sql.*;
import java.util.Arrays;

public class VehicleDao {
    private static final String CREATE_VEHICLE_QUERY =
            "INSERT INTO vehicles(model, make, dop, lic, next_service, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_VEHICLE_QUERY =
            "SELECT id, model, make, dop, lic, next_service, customer_id FROM vehicles WHERE id = ?";
    private static final String UPDATE_VEHICLE_QUERY =
            "UPDATE vehicles SET model = ?, make = ?, dop = ?, lic = ?, next_service = ?, customer_id = ? WHERE id = ?";
    private static final String DELETE_VEHICLE_QUERY =
            "DELETE FROM vehicles WHERE id = ?";
    private static final String FIND_ALL_VEHICLES_QUERY =
            "SELECT id, model, make, dop, lic, next_service, customer_id FROM vehicles";
    private static final String ADD_CUSTOMER_TO_VEHICLE =
            "UPDATE vehicles SET customer_id=? WHERE id =?;";
    private static final String FIND_ALL_VEHICLES_BY_CUSTOMER_QUERY =
            "SELECT id, model, make, dop, lic, next_service, customer_id FROM vehicles WHERE customer_id = ?";

    public void addCustomerToVehicle(Customer customer, Vehicle vehicle) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(ADD_CUSTOMER_TO_VEHICLE);) {

            statement.setInt(1, customer.getId());
            statement.setInt(2, vehicle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle create(Vehicle vehicle) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, vehicle.getModel());
            ps.setString(2, vehicle.getMake());
            ps.setDate(3, vehicle.getDop());
            ps.setString(4, vehicle.getLic());
            ps.setDate(5, vehicle.getNext_service());
            ps.setInt(6, vehicle.getCustomer_id());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                vehicle.setId(id);

                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Vehicle read(int vehicleId) {
        try (Connection conn = DbUtil.getConn();
             final PreparedStatement ps = conn.prepareStatement(READ_VEHICLE_QUERY);
        ) {
            ps.setInt(1, vehicleId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setDop(rs.getDate("dop"));
                vehicle.setLic(rs.getString("lic"));
                vehicle.setNext_service(rs.getDate("next_service"));
                vehicle.setCustomer_id(rs.getInt("customer_id"));

                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Vehicle vehicle) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(UPDATE_VEHICLE_QUERY);) {

            statement.setString(1, vehicle.getModel());
            statement.setString(2, vehicle.getMake());
            statement.setDate(3, vehicle.getDop());
            statement.setString(4, vehicle.getLic());
            statement.setDate(5, vehicle.getNext_service());
            statement.setInt(6, vehicle.getCustomer_id());
            statement.setInt(7, vehicle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int vehicleId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(DELETE_VEHICLE_QUERY)) {
            statement.setInt(1, vehicleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle[] findAllByCustomer(int customerId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_VEHICLES_BY_CUSTOMER_QUERY)) {
            statement.setInt(1, customerId);
            Vehicle[] vehicles = new Vehicle[0];
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setDop(rs.getDate("dop"));
                vehicle.setLic(rs.getString("lic"));
                vehicle.setNext_service(rs.getDate("next_service"));
                vehicle.setCustomer_id(rs.getInt("customer_id"));

                vehicles = addToArray(vehicle, vehicles);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vehicle[] findAll() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_VEHICLES_QUERY);) {
            Vehicle[] vehicles = new Vehicle[0];
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setMake(rs.getString("make"));
                vehicle.setDop(rs.getDate("dop"));
                vehicle.setLic(rs.getString("lic"));
                vehicle.setNext_service(rs.getDate("next_service"));
                vehicle.setCustomer_id(rs.getInt("customer_id"));

                vehicles = addToArray(vehicle, vehicles);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Vehicle[] addToArray(Vehicle v, Vehicle[] vehicles) {
        Vehicle[] tmpVehicles = Arrays.copyOf(vehicles, vehicles.length + 1);
        tmpVehicles [vehicles.length] = v;
        return tmpVehicles;
    }
}
