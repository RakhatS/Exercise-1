package kz.rakho.exercise1.services;

import kz.rakho.exercise1.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployee();
    List<Employee> findEmployeesBySearchText(String search_text);
    Employee saveEmployee(Employee employee);
    Employee findByEmail(String email);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String email);
}