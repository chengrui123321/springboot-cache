package com.wb.springboot.cache.service;

import com.wb.springboot.cache.bean.Employee;
import com.wb.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * EmployeeService
 *
 * @CacheConfig: 可以在类上为多个方法指定相同的cacheNames、keyGenerator、cacheManager
 */
@Service
@CacheConfig
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * @Cacheable: 开启查询缓存，将查询结果保存到缓存中
     *      value/cacheNames：指定缓存的名称
     *      key：指定存入缓存中的key的名称，可以为空，默认使用方法参数作为缓存的key，也可按照SpEL表达式进行设置
     *          #root.methodName/#root.method.name：获取方法名
     *          #root.target：获取目标对象
     *          #root.targetClass：获取目标类型
     *          #root.args：获取参数列表
     *          #iban 、 #a0 、  #p0 0代表参数索引
     *          #result：方法执行返回的结果
     *      keyGenerator：key生成器，可以指定自己的keyGenerator，但只需要和key配置一个
     *      cacheManager：缓存管理器，可以自定义
     *      condition：条件为true时，加入缓存
     *      unless：条件为true时，不会加入缓存
     *      sync：是否异步，指定为true就不能使用unless
     *
     */
    @Cacheable(cacheNames = "emp", key = "#id", cacheManager = "empCacheManager"/*key = "#root.method + '[' + #id + ']'"*/)
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        return employeeMapper.getEmp(id);
    }

    /**
     * @CachePut：既保证方法被调用，又保证数据可以被缓存，在方法调用后将数据缓存，一般用于修改
     *
     * 属性和@Cacheable一致
     */
    @CachePut(cacheNames = "emp", key = "#result.id"/*, keyGenerator = "keyGenerator"*/)
    public Employee updateEmp(Employee employee) {
        System.out.println("查询" + employee.getId() + "号员工");
        employeeMapper.update(employee);
        return employee;
    }

    /**
     * @CacheEvict: 清除缓存，默认清除指定的cacheNames的指定的key对应的缓存
     *    allEntries：true;清除所有的缓存
     *    beforeInvocation: true; 在执行方法之前就删除缓存，默认在执行方法后移除缓存，此时如果出现异常，则缓存没有移除
     */
    @CacheEvict(cacheNames = "emp", key = "#id", allEntries = true)
    public void deleteEmp(Integer id) {
        System.out.println("删除" + id + "号员工");
        employeeMapper.delete(id);
    }

}
