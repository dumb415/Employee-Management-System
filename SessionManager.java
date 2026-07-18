package com.ems.dao;

import com.ems.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int empId);
    int insert(Employee emp);   // returns generated emp_id
    void update(Employee emp);
    void delete(int empId);
}
