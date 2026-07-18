package com.ems.dao;

import com.ems.model.Department;
import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();
    void insert(Department dept);
    void update(Department dept);
    void delete(int deptId);
}
