package com.ems.dao;

import com.ems.config.DBConnection;
import com.ems.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String BASE_SELECT =
            "SELECT e.emp_id, e.name, e.email, e.phone, e.salary, e.hire_date, e.dept_id, d.dept_name " +
            "FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id ";

    private Employee map(ResultSet rs) throws SQLException {
        Date hd = rs.getDate("hire_date");
        Integer deptId = rs.getObject("dept_id") == null ? null : rs.getInt("dept_id");
        return new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getDouble("salary"),
                hd == null ? null : hd.toLocalDate(),
                deptId,
                rs.getString("dept_name"));
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "ORDER BY e.emp_id");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(int empId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(BASE_SELECT + "WHERE e.emp_id = ?");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? map(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee emp) {
        String sql = "INSERT INTO employees (name, email, phone, salary, hire_date, dept_id) VALUES (?,?,?,?,?,?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPhone());
            ps.setDouble(4, emp.getSalary());
            if (emp.getHireDate() == null) ps.setNull(5, Types.DATE);
            else ps.setDate(5, Date.valueOf(emp.getHireDate()));
            if (emp.getDeptId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, emp.getDeptId());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, email=?, phone=?, salary=?, hire_date=?, dept_id=? WHERE emp_id=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPhone());
            ps.setDouble(4, emp.getSalary());
            if (emp.getHireDate() == null) ps.setNull(5, Types.DATE);
            else ps.setDate(5, Date.valueOf(emp.getHireDate()));
            if (emp.getDeptId() == null) ps.setNull(6, Types.INTEGER);
            else ps.setInt(6, emp.getDeptId());
            ps.setInt(7, emp.getEmpId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int empId) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE emp_id = ?");
            ps.setInt(1, empId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
