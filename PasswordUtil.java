package com.ems.model;

public class Attendance {
    private int attId;
    private int empId;
    private String empName;
    private String attDate;
    private String checkIn;
    private String checkOut;
    private String status;

    public Attendance() {}

    public Attendance(int attId, int empId, String empName, String attDate,
                      String checkIn, String checkOut, String status) {
        this.attId = attId;
        this.empId = empId;
        this.empName = empName;
        this.attDate = attDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public int getAttId() { return attId; }
    public int getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getAttDate() { return attDate; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }
    public String getStatus() { return status; }

    public void setAttId(int attId) { this.attId = attId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public void setEmpName(String empName) { this.empName = empName; }
    public void setAttDate(String attDate) { this.attDate = attDate; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    public void setStatus(String status) { this.status = status; }
}
