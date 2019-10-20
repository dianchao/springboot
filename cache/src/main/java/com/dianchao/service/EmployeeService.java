package com.dianchao.service;

import com.dianchao.bean.Employee;
import com.dianchao.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//@CacheConfig注解抽取缓存的公共配置
//@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * @param id
     * @return
     * @Cacheable：注解作用是将方法返回的结果进行缓存，以后如果请求参数相同， 则直接从缓存中获取数据，不用调用方法
     * <p>
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字。
     * 1）cacheNames/value：指定缓存的名字;将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存。
     * 2）key：缓存数据使用的key，默认使用方法请求参数的值，如 1:返回值
     * 可以编写SPEL，#id 表示参数id的值; #a0; #po; #root.args[0]
     * key：方法名[参数值]
     * 3）keyGenerator：key生成器，可以自己指定key的生成器，注意key/keyGenerator 二选一使用
     * 4）condition：指定条件，只有当条件满足的情况下才缓存数据 如 condition = "#id>1";
     * condition = "#a0>1"：表示第一个参数的值大于0的时候才进行缓存
     * 5）unless：除非，当unless指定的条件为true，方法的返回值就不会被缓存；它可以获取到结果进行判断，如 #result == null
     * 6）sync：是否使用异步模式
     */
    //@Cacheable(cacheNames = {"emp", "tmp"})
    //@Cacheable(cacheNames = {"emp"}, key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0>1", unless = "#a0==2")
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmp(Integer id) {
        System.out.println("查询" + id + "号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @param employee
     * @return 修改了数据库中的数据同时更新缓存
     * 执行时机：先执行目标方法，然后将目标方法结果缓存起来
     * 注意：要保证更新缓存的key和查询缓存的key保持一致
     */
    //@CachePut(cacheNames = "emp", key = "employee.id")
    @CachePut(cacheNames = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp: " + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 缓存清除
     * 可以通过key指定要删除哪个key
     * allEntries = true：清除指定缓存中的所有数据
     * beforeInvocation = false：缓存的清除是否在方法执行之前执行，无论方法执行是否异常，缓存都会被清除。
     * 默认值为true：表示在方法执行之后清除缓存，如果在方法执行过程中出现异常，则缓存缓存就不会被清除。
     */
    //@CacheEvict(value = "emp", key = "#id")
    @CacheEvict(value = "emp", allEntries = true)
    public void deleteEmp(Integer id) {
        System.out.println("删除" + id + "号员工");
        //employeeMapper.deleteEmpById(id);
    }

    /**
     * 使用复杂的缓存注解
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {@Cacheable(value = "emp", key = "#lastName")},
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
