package com.dianchao.mapper;

import com.dianchao.bean.Employee;
import org.apache.ibatis.annotations.*;

public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getEmpById(Integer id);

    @Update("UPDATE employee SET lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} WHERE id=#{id}")
    void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName, email, gender, d_id) VALUES(#{lastName}, #{email}, #{gender}, #{dId})")
    void insertEmp(Employee employee);

    @Select(("SELECT * FROM employee WHERE lastName = #{lastName}"))
    Employee getEmpByLastName(String lastName);
}
