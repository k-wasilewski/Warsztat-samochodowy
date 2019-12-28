package org.workshop.dao;

import org.workshop.models.Customer;
import org.workshop.models.Status;

import java.sql.*;
import java.util.Arrays;

public class StatusDao {
    private static final String CREATE_STATUS_QUERY =
            "INSERT INTO statuses(status) VALUES (?)";
    private static final String READ_STATUS_QUERY =
            "SELECT id, status FROM statuses WHERE id = ?";
    private static final String UPDATE_STATUS_QUERY =
            "UPDATE statuses SET status = ? WHERE id = ?";
    private static final String DELETE_STATUS_QUERY =
            "DELETE FROM statuses WHERE id = ?";
    private static final String FIND_ALL_STATUSES_QUERY =
            "SELECT id, status FROM statuses";

    public Status create(Status status) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement ps = conn.prepareStatement(CREATE_STATUS_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, status.getStatus());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                status.setId(id);

                return status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Status read(int statusId) {
        try (Connection conn = DbUtil.getConn();
             final PreparedStatement ps = conn.prepareStatement(READ_STATUS_QUERY);
        ) {
            ps.setInt(1, statusId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("id"));
                status.setStatus(rs.getInt("status"));

                return status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Status status) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(UPDATE_STATUS_QUERY);) {

            statement.setInt(1, status.getStatus());
            statement.setInt(2, status.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int statusId) {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(DELETE_STATUS_QUERY)) {
            statement.setInt(1, statusId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Status[] findAll() {
        try (Connection conn = DbUtil.getConn();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_STATUSES_QUERY);) {
            Status[] statuses = new Status[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setStatus(resultSet.getInt("status"));

                statuses = addToArray(status, statuses);
            }
            return statuses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Status[] addToArray(Status s, Status[] statuses) {
        Status[] tmpStatuses = Arrays.copyOf(statuses, statuses.length + 1);
        tmpStatuses [statuses.length] = s;
        return tmpStatuses;
    }
}
