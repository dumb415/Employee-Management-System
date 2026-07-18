package com.ems.dao;

import com.ems.model.User;

public interface UserDAO {
    User findByUsername(String username);
    void insert(User user);
    void deleteByEmpId(int empId);
}
