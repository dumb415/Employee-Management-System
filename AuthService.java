package com.ems.dao;

import com.ems.config.DBConnection;
import com.ems.model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    private static final String BASE_SELECT =
            "SELECT a.att_id, a.emp_id, e.name, a.att_date, a.check_in, a.check_out, a.status " +
            "FROM attendance a JOIN employees e ON a.emp_id = e.emp_id ";

    private Attendance map(ResultSet rs) throws SQLException {
        return new Attendance(
                rs.getInt("att_id"),
                rs.getInt("emp_id"),
                rs.getString("name"),
                String.valueOf(rs.getDate("att_date")),
                rs.getTime("check_in") == null ? "-" : String.valueOf(rs.getTime("check_in")),
                rs.getTime("check_out") == null ? "-" : String.valueOf(rs.getTime("check_out")),
                rs.getString("status"));
    }

    @Override
    public List<Attendance> findAll() {
        List<Attendance> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "ORDER BY a.att_date DESC, e.name");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Attendance> findByEmployee(int empId) {
        List<Attendance> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "WHERE a.emp_id = ? ORDER BY a.att_date DESC");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasCheckedInToday(int empId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT 1 FROM attendance WHERE emp_id = ? AND att_date = CURDATE()");
            ps.setInt(1, empId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void checkIn(int empId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO attendance (emp_id, att_date, check_in, status) VALUES (?, CURDATE(), CURTIME(), 'PRESENT')");
            ps.setInt(1, empId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void checkOut(int empId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE attendance SET check_out = CURTIME() WHERE emp_id = ? AND att_date = CURDATE()");
            ps.setInt(1, empId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
