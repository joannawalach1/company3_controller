package pl.great.waw.company3.service;

import org.springframework.stereotype.Service;
import pl.great.waw.company3.controller.EmployeeDto;
import pl.great.waw.company3.domain.Employee;
import pl.great.waw.company3.domain.EmployeeData;
import pl.great.waw.company3.repository.EmployeeDataRepository;
import pl.great.waw.company3.repository.EmployeeRepository;
import pl.great.waw.company3.service.mapper.EmployeeDataMapper;
import pl.great.waw.company3.service.mapper.EmployeeMapper;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataRepository employeeDataRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeDataMapper employeeDataMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDataRepository employeeDataRepository, EmployeeMapper employeeMapper, EmployeeDataMapper employeeDataMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeDataRepository = employeeDataRepository;
        this.employeeMapper = employeeMapper;
        this.employeeDataMapper = employeeDataMapper;
    }

    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employeeSaved1 = employeeRepository.create(employeeMapper.fromDto(employeeDto));
        return employeeMapper.toDto(employeeSaved1);
    }

    public EmployeeDto get(String pesel) {
        Employee employee = employeeRepository.get(pesel);
        return employeeMapper.toDto(employee);
    }

    public boolean delete(String pesel) {
        return this.employeeRepository.delete(pesel);
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.fromDto(employeeDto);
        return employeeMapper.toDto(employeeRepository.update(employee));
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.getAllEmployees()
                .stream()
                .map(employee -> employeeMapper.toDto(employee))
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> sort(Comparator<Employee> comparator) {
        return this.employeeRepository.sortAllEmployees(comparator)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmployeeData> getEmployeeSalaryByMonthAndYear(String pesel, int month, int year) {
        return this.employeeDataRepository.getEmployeeSalaryByMonthAndYear(pesel, month, year)
                .stream()
                .map((EmployeeData employeeDataDto) -> EmployeeDataMapper.toDto(employeeDataDto))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalSalaryForAllEmployees() {
        return this.employeeDataRepository.getTotalSalaryForAllEmployees();
    }

    public BigDecimal getSalaryByPeselInYear(String pesel, int year) {
        return this.employeeDataRepository.getSalaryByPeselInYear(pesel, year);
    }

    public BigDecimal getTotalSalaryForEmployee(String pesel) {
        return this.employeeDataRepository.getTotalSalaryForEmployee(pesel);
    }

    public Map<Integer, BigDecimal> getTotalSalaryForEmployeeInAllYears(String pesel) {
        return this.employeeDataRepository.getTotalSalaryForEmployeeInAllYears(pesel);
    }
}
