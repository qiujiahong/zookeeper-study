package com.nick.service;


import com.nick.bean.Employee;

public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    Integer findEmployeeCount();
}
