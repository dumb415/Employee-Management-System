package com.ems.service;

import com.ems.dao.DepartmentDAO;
import com.ems.dao.DepartmentDAOImpl;
import com.ems.model.Department;
import com.ems.util.InputValidator;

import java.util.List;

public class DepartmentService {

    private final DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public List<Department> getAll() { return departmentDAO.findAll(); }

    public void add(String name) {
        if (!InputValidator.isNotBlank(name))
            throw new IllegalArgumentException("Department name is required.");
        departmentDAO.insert(new Department(0, name.trim()));
    }

    public void rename(int deptId, String name) {
        if (!InputValidator.isNotBlank(name))
            throw new IllegalArgumentException("Department name is required.");
        departmentDAO.update(new Department(deptId, name.trim()));
    }

    public void delete(int deptId) { departmentDAO.delete(deptId); }
}
