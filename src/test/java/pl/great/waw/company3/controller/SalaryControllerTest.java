package pl.great.waw.company3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.great.waw.company3.domain.EmployeeData;
import pl.great.waw.company3.service.EmployeeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SalaryControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    SalaryController salaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployeeSalaryByMonthAndYear() {
        String pesel = "1234567890";
        int month = 4;
        int year = 2023;
        List<EmployeeData> employeeDataList = new ArrayList<>();
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