package com.dianchao.controller;

import com.dianchao.bean.Employee;
import com.dianchao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/updateEmp")
    public Employee updateEmployee(Employee employee){
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/deleteEmp")
    public String deleteEmployee(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/getEmpByLastName/{lastName}")
    public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
