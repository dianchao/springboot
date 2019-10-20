package com.dianchao.service;

import com.dianchao.bean.Department;
import com.dianchao.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Cacheable(cacheNames = {"dept"}, cacheManager = "departmentCacheManager")
    public Department getDeptById(Integer id){
        System.out.println("查询" + id + "部门");
        return departmentMapper.getDepartmentById(id);
    }
}
