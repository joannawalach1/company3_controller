package pl.great.waw.company3.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmployeeData {
    private String id;
    private String employeePesel;
    private int month;
    private int year;
    private BigDecimal salaryMonth;
    private LocalDateTime created;
    private LocalDateTime updated;


    public EmployeeData(String id, String employeePesel, int month, int year, BigDecimal salaryMonth, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.employeePesel = employeePesel;
        this.month = month;
        this.year = year;
        this.salaryMonth = salaryMonth;
        this.created = created;
        this.updated = updated;
    }

    public EmployeeData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeePesel() {
        return employeePesel;
    }

    public void setEmployeePesel(String employeePesel) {
        this.employeePesel = employeePesel;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(BigDecimal salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeData that = (EmployeeData) o;
        return month == that.month && year == that.year && Objects.equals(id, that.id) && Objects.equals(employeePesel, that.employeePesel) && Objects.equals(salaryMonth, that.salaryMonth) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeePesel, month, year, salaryMonth, created, updated);
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "id='" + id + '\'' +
                ", employeePesel='" + employeePesel + '\'' +
                ", month=" + month +
                ", year=" + year +
                ", salaryMonth=" + salaryMonth +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
