package com.employeeexcample.Employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeexcample.Employee.model.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    ArrayList<Employee> employeeList = new ArrayList<>();

    @GetMapping("get-all-employee")
    public List<Employee> getAllEmployee() {

        return employeeList;
    }

    @GetMapping("get-employee-by-id/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeList.stream().filter(x -> x.getId().equals(employeeId)).findFirst().get();

        // ************************another way********************************
        // Employee x;
        // employee.forEach(result ->{
        // if(result.getId() == employeeId){
        // System.out.println("The employee is : " + employee);
        // x= result;

        // }
        // else
        // System.out.println("There is no employee by this Id");
        // });
        // return x;

    }

    @PostMapping("add-employee")
    public void addEmployee(@RequestBody Employee model) {
        employeeList.add(model);

    }

    @PutMapping("/update-employee/{employeeId}")
    public void updateEmployee(@RequestBody Employee model, @PathVariable int employeeId) {
        var employee = employeeList.stream().filter(x -> x.getId().equals(employeeId)).findFirst().get();
        employee.setName(model.getName());
        employee.setAge(model.getAge());

    }

    @DeleteMapping("/delete-employee/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        var employee = employeeList.stream().filter(x -> x.getId().equals(employeeId)).findFirst().get();
        employeeList.remove(employee);
    }

}
