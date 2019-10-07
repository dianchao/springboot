package com.dianchao.controller;

import com.dianchao.dao.DepartmentDao;
import com.dianchao.dao.EmployeeDao;
import com.dianchao.entities.Department;
import com.dianchao.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    //结果放入到Model、ModelMap、Map中都可以
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> emps = employeeDao.getAll();
        employeeDao.getAll();
        //结果放到请求域中
        model.addAttribute("emps", emps);
        //thymeleaf默认就会拼串 prefix: /templates/  suffix: .html
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //来到添加页面, 查出所有的部门，在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和JavaBean入参对象里面的属性名一致
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //来到员工列表页面
        System.out.println("保存的员工信息: " + employee);
        //保存员工
        employeeDao.save(employee);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面,查询当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        //页面要显示所有部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //回到修改页面(add页面是一个修改和添加二合一的页面)
        return "emp/add";
    }

    //员工修改; 需要提交员工的id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        System.out.println("修改的员工数据：" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
