package dev.peertosir.payroll.controller;

import dev.peertosir.payroll.exception.EmployeeNotFoundException;
import dev.peertosir.payroll.model.Employee;
import dev.peertosir.payroll.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/emps")
    List<Employee> getEmployees() {
        return repository.findAll();
    }

    @GetMapping("/emps/{id}")
    Employee getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()){
            return employee.get();
        }
        throw new EmployeeNotFoundException(id);
    }

    @PostMapping("/emps")
    Employee createEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @PutMapping("/emps/{id}")
    Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return repository.findById(id).map(emp -> {
            emp.setName(employee.getName());
            emp.setRole(employee.getRole());
            return repository.save(emp);
        }).orElseGet(() -> {
            employee.setId(id);
            return repository.save(employee);
        });
    }

    @DeleteMapping("/emps/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
