package kz.rakho.exercise1.services.impl;

import kz.rakho.exercise1.models.Employee;
import kz.rakho.exercise1.repositories.EmployeeRepository;
import kz.rakho.exercise1.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAllEmployee() {
        return repository.findAll();
    }

    @Override
    public List<Employee> findEmployeesBySearchText(String search_text) {
        List<Employee> employees = repository.findAll();

        employees = employees.stream()
                .filter(n ->
                        n.getEmail().contains(search_text) || n.getCity().contains(search_text)
                        || n.getFirstName().contains(search_text) || n.getLastName().contains(search_text)
                        || n.getSurName().contains(search_text) || n.getCountry().contains(search_text)
                        || n.getCountry().contains(search_text))
                .collect(Collectors.toList());

        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return repository.findEmployeeByEmail(email);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(String email) {
        repository.deleteByEmail(email);
    }
}