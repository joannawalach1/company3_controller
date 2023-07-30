package pl.great.waw.company3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.great.waw.company3.domain.EmployeeData;
import pl.great.waw.company3.repository.EmployeeDataRepository;
import pl.great.waw.company3.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SalaryControllerTest {
private List<EmployeeData> employeeDataList;
    @Mock
    EmployeeService employeeService;

    @Mock
    EmployeeDataRepository employeeDataRepository;

    @InjectMocks
    SalaryController salaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<EmployeeData> employeeDataList = new ArrayList<>();
        EmployeeData employee1 = new EmployeeData("1", "1234567890", 4, 2023, BigDecimal.valueOf(2000), LocalDateTime.now(), LocalDateTime.now());
        EmployeeData employee2 = new EmployeeData("2", "1234567890", 5, 2023, BigDecimal.valueOf(3000), LocalDateTime.now(), LocalDateTime.now());
        employeeDataList.add(employee1);
        employeeDataList.add(employee2);
    }

    @Test
    void getEmployeeSalaryByMonthAndYear() {
        String pesel = "1234567890";
        int month = 4;
        int year = 2023;
        when(employeeService.getEmployeeSalaryByMonthAndYear(pesel, month, year)).thenReturn(employeeDataList);
        List<EmployeeData> resultList = salaryController.getEmployeeSalaryByMonthAndYear(pesel, month, year);
        assertEquals(employeeDataList, resultList);
    }

    @Test
    void getSalaryByPeselInYear() {
        String pesel = "1234567890";
        int year = 2023;
        BigDecimal totalSalary = new BigDecimal("50000");
        when(employeeService.getSalaryByPeselInYear(pesel, year)).thenReturn(totalSalary);
        ResponseEntity<BigDecimal> resultList = salaryController.getSalaryByPeselInYear(pesel, year);
        assertEquals(HttpStatus.OK, resultList.getStatusCode());
        assertEquals(totalSalary, salaryController.getSalaryByPeselInYear(pesel, year).getBody());
    }

    @Test
    void getTotalSalaryForEmployee() {
        String pesel = "1234567890";
        BigDecimal totalSalary = new BigDecimal("50000");
        when(employeeService.getTotalSalaryForEmployee(pesel)).thenReturn(totalSalary);
        BigDecimal result = salaryController.getTotalSalaryForEmployee(pesel);
        assertEquals(totalSalary, result);
    }

    @Test
    void getTotalSalaryForAllEmployees() {
        BigDecimal totalSalary = new BigDecimal("5000");
        when(employeeService.getTotalSalaryForAllEmployees()).thenReturn(totalSalary);
        BigDecimal result = salaryController.getTotalSalaryForAllEmployees();
        assertEquals(totalSalary, result);
    }
}