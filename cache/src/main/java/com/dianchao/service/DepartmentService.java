package com.dianchao.service;

import com.dianchao.bean.Department;
import com.dianchao.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("departmentCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

//    @Cacheable(cacheNames = {"dept"}, cacheManager = "departmentCacheManager")
//    public Department getDeptById(Integer id){
//        System.out.println("查询" + id + "部门");
//        return departmentMapper.getDepartmentById(id);
//    }

    //编码方式操作缓存
    public Department getDeptById(Integer id){
        System.out.println("查询" + id + "部门");
        Department department = departmentMapper.getDepartmentById(id);
        //编码方式进行缓存操作
        deptCacheManager.getCache("dept").put("dept:1", department);
        return department;
    }
}
