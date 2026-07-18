package com.ems.service;

import com.ems.dao.EmployeeDAO;
import com.ems.dao.EmployeeDAOImpl;
import com.ems.model.Employee;
import com.ems.util.InputValidator;

import java.util.List;

public class EmployeeService {

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    public List<Employee> getAll() { return employeeDAO.findAll(); }

    public Employee getById(int empId) { return employeeDAO.findById(empId); }

    /** Adds an employee and auto-creates a login (username = email, password = emp123). */
    public int add(Employee emp) {
        validate(emp);
        int empId = employeeDAO.insert(emp);
        AuthService.registerEmployeeAccount(emp.getEmail(), "emp123", empId);
        return empId;
    }

    public void update(Employee emp) {
        validate(emp);
        employeeDAO.update(emp);
    }

    public void delete(int empId) {
        employeeDAO.delete(empId);  // users row cascades via FK
    }

    public double totalPayroll() {
        return getAll().stream().mapToDouble(Employee::getSalary).sum();
    }

    private void validate(Employee emp) {
        if (!InputValidator.isNotBlank(emp.getName()))
            throw new IllegalArgumentException("Name is required.");
        if (!InputValidator.isValidEmail(emp.getEmail()))
            throw new IllegalArgumentException("Invalid email address.");
        if (emp.getSalary() < 0)
            throw new IllegalArgumentException("Salary cannot be negative.");
    }
}
