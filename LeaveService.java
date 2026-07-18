package com.ems.dao;

import com.ems.model.LeaveRequest;
import java.time.LocalDate;
import java.util.List;

public interface LeaveDAO {
    List<LeaveRequest> findAll();
    List<LeaveRequest> findByEmployee(int empId);
    void insert(int empId, LocalDate start, LocalDate end, String reason);
    void updateStatus(int leaveId, String status);
}
