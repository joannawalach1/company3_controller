package pl.great.waw.company3.repository;

import org.springframework.stereotype.Repository;
import pl.great.waw.company3.domain.EmployeeData;
import pl.great.waw.company3.repository.sorter.BubbleSort;
import pl.great.waw.company3.repository.sorter.Sorter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeDataRepository {

    private final List<EmployeeData> employeeDataFromRepo = new ArrayList<>();
    private final Sorter sorter = new BubbleSort();

    public EmployeeData createData(EmployeeData employeeData) {
        String pesel = employeeData.getEmployeePesel();
        boolean peselExists = employeeDataFromRepo.stream()
                .anyMatch(data -> data.getEmployeePesel().equals(pesel));

        if (peselExists) {
            throw new IllegalArgumentException("Pesel exists: " + pesel);
        }

        employeeDataFromRepo.add(employeeData);
        return employeeData;
    }

    private boolean peselExists(String pesel) {
        return employeeDataFromRepo
                .stream()
                .anyMatch(data -> data.getEmployeePesel().equals(pesel));
    }

    public void createAll(List<EmployeeData> employeesToCreate){
        employeeDataFromRepo.addAll(employeesToCreate);
    }
    public List<EmployeeData> getData(String employeeId) {
        return employeeDataFromRepo.stream()
                .filter(employeeData -> employeeData.getId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public List<EmployeeData> getEmployeeSalaryByMonthAndYear(String employeePesel, int month, int year) {
        return employeeDataFromRepo.stream()
                .filter(employeeData -> employeeData.getEmployeePesel().equals(employeePesel))
                .filter(employeeData -> Objects.equals(employeeData.getMonth(), month) && Objects.equals(employeeData.getYear(), year))
                .collect(Collectors.toList());
    }

    public BigDecimal getSalaryByPeselInYear(String pesel, int year) {
        BigDecimal totalSalary = employeeDataFromRepo.stream()
                .filter(employeeData -> employeeData.getEmployeePesel().equals(pesel) && employeeData.getYear() == year)
                .map(EmployeeData::getSalaryMonth)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSalary;
    }

    public BigDecimal getTotalSalaryForEmployee(String pesel) {
        BigDecimal totalSalary = employeeDataFromRepo.stream()
                .filter(employeeData -> employeeData.getEmployeePesel().equals(pesel))
                .map(EmployeeData::getSalaryMonth)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSalary;
    }

    public Map<Integer, BigDecimal> getTotalSalaryForEmployeeInAllYears(String pesel) {
        Map<Integer, BigDecimal> totalSalaryByYear = employeeDataFromRepo.stream()
                .filter(employeeData -> employeeData.getEmployeePesel().equals(pesel))
                .collect(Collectors.groupingBy(EmployeeData::getYear,
                        Collectors.mapping(EmployeeData::getSalaryMonth, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return totalSalaryByYear;
    }

    public BigDecimal getTotalSalaryForAllEmployees() {
        BigDecimal totalSalary = employeeDataFromRepo.stream()
                .map(EmployeeData::getSalaryMonth)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSalary;
    }

    public EmployeeData updateData(String employeeId, EmployeeData employeeData) {
        List<EmployeeData> employeeDataList = getData(employeeId);
        Optional<EmployeeData> oldData = employeeDataList.stream()
                .filter(data -> Objects.equals(data.getMonth(), employeeData.getMonth()))
                .findFirst();

        oldData.ifPresent(old -> {
            int index = employeeDataList.indexOf(old);
            employeeDataList.set(index, employeeData);
        });

        return employeeData;
    }

    public void deleteData(String employeeId) {
        employeeDataFromRepo.removeAll(getData(employeeId));
    }

    public void deleteAll(){
        this.employeeDataFromRepo.clear();
    }

    public List<EmployeeData> getAll() {
        return employeeDataFromRepo;
    }

    public int sizeData() {
        return employeeDataFromRepo.size();
    }
}