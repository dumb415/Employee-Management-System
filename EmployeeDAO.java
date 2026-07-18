package com.ems.model;

public class LeaveRequest {
    private int leaveId;
    private int empId;
    private String empName;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;

    public LeaveRequest() {}

    public LeaveRequest(int leaveId, int empId, String empName, String startDate,
                        String endDate, String reason, String status) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.empName = empName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public int getLeaveId() { return leaveId; }
    public int getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    public void setLeaveId(int leaveId) { this.leaveId = leaveId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public void setEmpName(String empName) { this.empName = empName; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
}
