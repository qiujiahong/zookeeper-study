package com.nick.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.nick.bean.Employee;
import com.nick.provider.dao.EmployeeDao;
import com.nick.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;



@Service
@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //    清理所有缓存
    @CacheEvict(value = "realTimeCache", allEntries = true)
    @Transactional
    @Override
    public void addEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

    //    @Cacheable(value = "realTimeCache", key = "'employee_'+#id")
    @Cacheable(value = "realTimeCache")
    @Override
    public Employee findEmployeeById(int id) {
        return employeeDao.selectEmployeeById(id);
    }

    //    使用双重检测锁，解决热点缓存问题
    @Override
    public Integer findEmployeeCount() {

        //获取redis操作对象
        BoundValueOperations<Object, Object> ops = redisTemplate.boundValueOps("count");
        //        从缓存中读数据
        Object count = ops.get();
        if (count == null) {
            synchronized (this) {
                count = ops.get();
                if (count == null) {//从db中查询
                    count = employeeDao.selectEmployeeCount();
                    //将查询的数据写入到redis缓存，并设置到期时间
                    ops.set(count, 60, TimeUnit.SECONDS);
                }
            }
        }
        return (Integer) count;
    }
}
