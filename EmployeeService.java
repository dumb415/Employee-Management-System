package com.ems.dao;

import com.ems.config.DBConnection;
import com.ems.model.LeaveRequest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaveDAOImpl implements LeaveDAO {

    private static final String BASE_SELECT =
            "SELECT l.leave_id, l.emp_id, e.name, l.start_date, l.end_date, l.reason, l.status " +
            "FROM leave_requests l JOIN employees e ON l.emp_id = e.emp_id ";

    private LeaveRequest map(ResultSet rs) throws SQLException {
        return new LeaveRequest(
                rs.getInt("leave_id"),
                rs.getInt("emp_id"),
                rs.getString("name"),
                String.valueOf(rs.getDate("start_date")),
                String.valueOf(rs.getDate("end_date")),
                rs.getString("reason"),
                rs.getString("status"));
    }

    @Override
    public List<LeaveRequest> findAll() {
        List<LeaveRequest> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "ORDER BY l.leave_id DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LeaveRequest> findByEmployee(int empId) {
        List<LeaveRequest> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "WHERE l.emp_id = ? ORDER BY l.leave_id DESC");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(int empId, LocalDate start, LocalDate end, String reason) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO leave_requests (emp_id, start_date, end_date, reason) VALUES (?,?,?,?)");
            ps.setInt(1, empId);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));
            ps.setString(4, reason);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatus(int leaveId, String status) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE leave_requests SET status=? WHERE leave_id=?");
            ps.setString(1, status);
            ps.setInt(2, leaveId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
