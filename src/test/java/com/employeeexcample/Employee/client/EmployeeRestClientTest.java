package com.employeeexcample.Employee.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeexcample.Employee.model.Employee;

public class EmployeeRestClientTest {

   private static final String baseUrl = "http://localhost:8080/api/employee";

   // private WebClient webClient = WebClient.builder().build();
   private WebClient webclient = WebClient.create(baseUrl);

   private EmployeeRestClient employeeRestClient = new EmployeeRestClient(webclient);

   @Test
   void getAllEmployee() {
      List<Employee> employeeList = employeeRestClient.getAllEmployee();
      System.out.println("employee List : " + employeeList);
      assertTrue(employeeList.size() > 0);
   }
   // ***********************another way***********************
   // @Test
   // Flux<Employee> getAllEmployee(){
   // return this.webClient.get()
   // .uri(baseUrl + GET_ALL_EMPLOYEE)
   // .retrieve()
   // .bodyToFlux(Employee.class);
   // }

   @Test
   void getEmployeeById() {
      int employeeId = 1;
      Employee employee = employeeRestClient.getEmployeeById(employeeId);
      assertEquals("Sara", employee.getName());
   }

   @Test
   void addEmployee() { // before run this test delete @RequestBody from api pot in controller 
      Employee employee = new Employee(3, "Abdullah", 27);
      employeeRestClient.addEmployee(employee);
      assertTrue(employee.getId() != null);
   }

   @Test
   void updateEmployee() {
      Employee employee = new Employee(null,"Lara",25);
      employeeRestClient.updateEmployee(employee, 1);
   }

   @Test
    void deleteEmployee(){
      int employeeId = 1;   
      employeeRestClient.deleteEmployee(employeeId);
   }

}
