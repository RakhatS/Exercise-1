package kz.rakho.exercise1.controllers;

import kz.rakho.exercise1.helpers.EmailValidator;
import kz.rakho.exercise1.helpers.PhoneNumberValidator;
import kz.rakho.exercise1.models.Employee;
import kz.rakho.exercise1.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
public class EmployeeController {


    private final EmployeeService service;


    @GetMapping
    public List<Employee> findAllEmployee(@RequestParam String search_text) {
        if (search_text != null && !search_text.isEmpty()) {

            return service.findEmployeesBySearchText(search_text);
        } else {
            // If no search_text is provided, return all employees
            return service.findAllEmployee();
        }
    }

    @PostMapping("save_employee")
    public void saveEmployee(@Valid @RequestBody Employee employee) throws BadRequestException {
        boolean isValidEmail = EmailValidator.validateEmail(employee.getEmail());
        boolean isValidPhoneNumber = PhoneNumberValidator.isValidPhoneNumber(employee.getPhoneNumber());

        if (!isValidEmail) {
            throw new BadRequestException("Invalid email value");
        }
        if (!isValidPhoneNumber) {
            throw new BadRequestException("Invalid phone number value");
        }

        service.saveEmployee(employee);

    }

    @GetMapping("/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }


    @PutMapping("update_employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("delete_employee/{email}")
    public void deleteEmployee(@PathVariable String email) {
        service.deleteEmployee(email);
    }
}
