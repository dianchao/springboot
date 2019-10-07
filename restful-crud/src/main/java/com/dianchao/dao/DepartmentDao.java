package com.dianchao.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.dianchao.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "开发部"));
		departments.put(102, new Department(102, "测试部"));
		departments.put(103, new Department(103, "架构部"));
		departments.put(104, new Department(104, "质量部"));
		departments.put(105, new Department(105, "市场部"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}
