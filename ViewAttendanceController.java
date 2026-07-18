package com.ems.dao;

import com.ems.model.Attendance;
import java.util.List;

public interface AttendanceDAO {
    List<Attendance> findAll();
    List<Attendance> findByEmployee(int empId);
    boolean hasCheckedInToday(int empId);
    void checkIn(int empId);
    void checkOut(int empId);
}
