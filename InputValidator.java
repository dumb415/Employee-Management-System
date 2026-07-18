package com.ems.model;

import java.time.LocalDate;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private String phone;
    private double salary;
    private LocalDate hireDate;
    private Integer deptId;
    private String deptName;  // joined from departments

    public Employee() {}

    public Employee(int empId, String name, String email, String phone,
                    double salary, LocalDate hireDate, Integer deptId, String deptName) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.hireDate = hireDate;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}
