package pl.great.waw.company3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.great.waw.company3.domain.EmployeeData;
import pl.great.waw.company3.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{pesel}{month}{year}")
    public List<EmployeeData> getEmployeeSalaryByMonthAndYear(@PathVariable String pesel, @PathVariable int month, @PathVariable int year) {
        return employeeService.getEmployeeSalaryByMonthAndYear(pesel, month, year);
    }

    @GetMapping(value = "/{pesel}/{year}")
    public ResponseEntity<BigDecimal> getSalaryByPeselInYear(@PathVariable String pesel, @PathVariable int year) {
        BigDecimal totalSalary = this.employeeService.getSalaryByPeselInYear(pesel, year);
        if (totalSalary.compareTo(BigDecimal.ZERO) > 0) {
            return ResponseEntity.ok(totalSalary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{pesel}")
    public BigDecimal getTotalSalaryForEmployee(String pesel) {
        return employeeService.getTotalSalaryForEmployee(pesel);
    }

    @GetMapping(value = "/salary/{pesel}")
    public BigDecimal getTotalSalaryForAllEmployees() {
        return employeeService.getTotalSalaryForAllEmployees();
    }
}