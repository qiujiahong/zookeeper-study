package com.nick.redisdemo.service;


import com.nick.redisdemo.bean.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    Integer findEmployeeCount();
}
