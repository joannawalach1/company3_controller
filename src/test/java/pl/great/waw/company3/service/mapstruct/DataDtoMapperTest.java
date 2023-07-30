package pl.great.waw.company3.service.mapstruct;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.great.waw.company3.controller.EmployeeDataDto;
import pl.great.waw.company3.domain.EmployeeData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DataDtoMapperTest {
    EmployeeDataDto employeeDataDto = new EmployeeDataDto();
    private DataDtoMapper mapper
            = Mappers.getMapper(DataDtoMapper.class);
    @Test
    void employeeDataDtoToEmployeeData() {

        employeeDataDto.setId("1");
        employeeDataDto.setEmployeePesel("1234567890");
        employeeDataDto.setMonth(4);
        employeeDataDto.setYear(2022);
        employeeDataDto.setMonthSalary(BigDecimal.valueOf(10000));
        employeeDataDto.setCreated(LocalDateTime.now());
        employeeDataDto.setUpdated(LocalDateTime.now());

             EmployeeData employeeData = mapper.employeeDataToEmployeeDataDto(employeeDataDto);

        assertEquals(employeeDataDto.getEmployeePesel(), employeeData.getEmployeePesel());
        assertEquals(employeeDataDto.getId(),
                employeeData.getId());
    }

    @Test
    void employeeDataToEmployeeDataDto() {
        EmployeeData employeeData = new EmployeeData(employeeDataDto.getId(), employeeDataDto.getEmployeePesel(), employeeDataDto.getMonth(), employeeDataDto.getYear(), employeeDataDto.getMonthSalary(), employeeDataDto.getCreated(), employeeDataDto.getUpdated());
        employeeData.setId("2");
        employeeData.setEmployeePesel("1234567891");
        employeeData.setMonth(2);
        employeeData.setYear(4);
        employeeData.setSalaryMonth(BigDecimal.valueOf(12000));
        EmployeeDataDto employeeDataDto = mapper.employeeDataDtoToEmployeeData(employeeData);
        assertEquals(employeeDataDto.getId(), employeeDataDto.getId());
        assertEquals(employeeDataDto.getEmployeePesel(),
                employeeData.getEmployeePesel());
    }
    }
