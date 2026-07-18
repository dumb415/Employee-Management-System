package com.ems.service;

import com.ems.dao.UserDAO;
import com.ems.dao.UserDAOImpl;
import com.ems.model.User;
import com.ems.util.PasswordUtil;

public class AuthService {

    private static final UserDAO userDAO = new UserDAOImpl();

    /** Returns the user on success, null on bad credentials. */
    public static User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) return null;
        return PasswordUtil.verify(password, user.getPasswordHash()) ? user : null;
    }

    /** Creates a login account linked to an employee. */
    public static void registerEmployeeAccount(String username, String plainPassword, int empId) {
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(PasswordUtil.hash(plainPassword));
        user.setRole("EMPLOYEE");
        user.setEmpId(empId);
        userDAO.insert(user);
    }

    public static void deleteAccountForEmployee(int empId) {
        userDAO.deleteByEmpId(empId);
    }
}
