package kz.rakho.exercise1.repositories;

import kz.rakho.exercise1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    void deleteByEmail(String email);
    Employee findEmployeeByEmail(String email);
}
