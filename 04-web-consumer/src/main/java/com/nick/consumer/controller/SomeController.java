package com.nick.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nick.bean.Employee;
import com.nick.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class SomeController {

//    @Autowired
    @Reference
    private EmployeeService employeeService;

    @PostMapping("/register")
    public String add(Employee employee){
        employeeService.addEmployee(employee);
        return "page/welcome";
    }

    @PostMapping("/find")
    @ResponseBody
    public Employee find(int id){
        return employeeService.findEmployeeById(id);
    }


    @GetMapping("/count")
    @ResponseBody
    public Integer count(){
        return employeeService.findEmployeeCount();
    }
}
