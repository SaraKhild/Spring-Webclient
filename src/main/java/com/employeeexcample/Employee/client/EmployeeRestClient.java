package com.employeeexcample.Employee.client;

import static com.employeeexcample.Employee.constants.EmployeeConstants.ADD_EMPLOYEE;
import static com.employeeexcample.Employee.constants.EmployeeConstants.DELETE_EMPLOYEE;
import static com.employeeexcample.Employee.constants.EmployeeConstants.GET_ALL_EMPLOYEE;
import static com.employeeexcample.Employee.constants.EmployeeConstants.GET_EMPLOYEE_BY_ID;
import static com.employeeexcample.Employee.constants.EmployeeConstants.UPDATE_EMPLOYEE;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.employeeexcample.Employee.model.Employee;

public class EmployeeRestClient {

    // @Autowired
    private WebClient webClient;

    public EmployeeRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Employee> getAllEmployee() {
        return webClient.get().uri(GET_ALL_EMPLOYEE)
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList()
                .block();

    }

    public Employee getEmployeeById(int employeeId) {
      
        return webClient.get()
        .uri(GET_EMPLOYEE_BY_ID, employeeId)
        .retrieve()
        .bodyToMono(Employee.class)
        .block();
   
       }

    public Employee addEmployee(Employee model) {
        return webClient.post()
        .uri(ADD_EMPLOYEE)
        .retrieve()
        .bodyToMono(Employee.class)
        .block();
    }

    public void updateEmployee(Employee model, int employeeId) {
         webClient.put().uri(UPDATE_EMPLOYEE, employeeId);
    }

    public void deleteEmployee(int employeeId){
        webClient.delete().uri(DELETE_EMPLOYEE, employeeId);
    }

}
