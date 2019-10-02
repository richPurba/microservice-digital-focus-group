package com.accenture.microservice.aggregator;

public class Salary {

    private String Department;
    private String Employee;
    private String euro;

    public String getEuro() {
        return euro;
    }

    public void setEuro(String euro) {
        this.euro = euro;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }
}
