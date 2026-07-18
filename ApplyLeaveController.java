package com.ems.service;

import com.ems.dao.LeaveDAO;
import com.ems.dao.LeaveDAOImpl;
import com.ems.model.LeaveRequest;
import com.ems.util.InputValidator;

import java.time.LocalDate;
import java.util.List;

public class LeaveService {

    private final LeaveDAO leaveDAO = new LeaveDAOImpl();

    public List<LeaveRequest> getAll() { return leaveDAO.findAll(); }

    public List<LeaveRequest> getForEmployee(int empId) { return leaveDAO.findByEmployee(empId); }

    public void apply(int empId, LocalDate start, LocalDate end, String reason) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Start and end dates are required.");
        if (end.isBefore(start))
            throw new IllegalArgumentException("End date cannot be before start date.");
        if (!InputValidator.isNotBlank(reason))
            throw new IllegalArgumentException("Reason is required.");
        leaveDAO.insert(empId, start, end, reason.trim());
    }

    public void approve(int leaveId) { leaveDAO.updateStatus(leaveId, "APPROVED"); }
    public void reject(int leaveId) { leaveDAO.updateStatus(leaveId, "REJECTED"); }
}
