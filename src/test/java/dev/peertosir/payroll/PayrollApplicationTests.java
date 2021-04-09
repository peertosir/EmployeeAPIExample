package dev.peertosir.payroll;

import dev.peertosir.payroll.controller.EmployeeController;
import dev.peertosir.payroll.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayrollApplicationTests {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {
        assertThat(employeeController).isNotNull();
        assertThat(employeeRepository).isNotNull();
    }

}
