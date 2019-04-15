package com.nick.provider.dao;


import com.nick.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeDao {

    void insertEmployee(Employee employee) ;

    Integer selectEmployeeCount();

    Employee selectEmployeeById(@Param("id") int id);
}
