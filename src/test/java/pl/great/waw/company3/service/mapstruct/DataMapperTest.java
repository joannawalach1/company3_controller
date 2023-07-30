package pl.great.waw.company3.service.mapstruct;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.great.waw.company3.controller.EmployeeDto;
import pl.great.waw.company3.domain.Employee;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataMapperTest {

    private DataMapper mapper
            = Mappers.getMapper(DataMapper.class);

    @Test
    public void employeeDtoToEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setPesel("1234567890");
        employeeDto.setFirstName("Kate");
        employeeDto.setLastName("Windsor");
        employeeDto.setSalary(BigDecimal.valueOf(2000));

        Employee employee = mapper.dtoToEmployeeData(employeeDto);

        assertEquals(employeeDto.getPesel(), employee.getPesel());
        assertEquals(employeeDto.getLastName(),
                employee.getLastName());
    }

    @Test
    public void employeeToEmployeeDtoTest() {
        Employee employee = new Employee();
        employee.setPesel("1234567890");
        employee.setFirstName("Steve");
        employee.setLastName("Brown");
        employee.setSalary(BigDecimal.valueOf(3000));
        EmployeeDto employeeDto = mapper.employeeToDto(employee);
        assertEquals(employee.getPesel(), employeeDto.getPesel());
        assertEquals(employee.getLastName(),
                employee.getLastName());
    }
}