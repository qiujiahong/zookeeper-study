package com.nick.redisdemo.dao;


import com.nick.redisdemo.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeDao {

    void insertEmployee(Employee employee) ;

    Integer selectEmployeeCount();

    Employee selectEmployeeById(@Param("id")int id);
}
