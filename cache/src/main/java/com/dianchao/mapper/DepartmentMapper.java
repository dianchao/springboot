package com.dianchao.mapper;

import com.dianchao.bean.Department;
import org.apache.ibatis.annotations.Select;

public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDepartmentById(Integer id);
}
