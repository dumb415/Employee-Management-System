package com.ems.service;

import com.ems.dao.AttendanceDAO;
import com.ems.dao.AttendanceDAOImpl;
import com.ems.model.Attendance;

import java.util.List;

public class AttendanceService {

    private final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();

    public List<Attendance> getAll() { return attendanceDAO.findAll(); }

    public List<Attendance> getForEmployee(int empId) { return attendanceDAO.findByEmployee(empId); }

    public void checkIn(int empId) {
        if (attendanceDAO.hasCheckedInToday(empId))
            throw new IllegalStateException("Already checked in today.");
        attendanceDAO.checkIn(empId);
    }

    public void checkOut(int empId) {
        if (!attendanceDAO.hasCheckedInToday(empId))
            throw new IllegalStateException("You have not checked in today.");
        attendanceDAO.checkOut(empId);
    }
}
