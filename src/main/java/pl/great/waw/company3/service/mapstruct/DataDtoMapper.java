package pl.great.waw.company3.service.mapstruct;

import org.mapstruct.Mapper;
import pl.great.waw.company3.controller.EmployeeDataDto;
import pl.great.waw.company3.domain.EmployeeData;

@Mapper(componentModel = "spring")
public interface DataDtoMapper {
    EmployeeDataDto employeeDataDtoToEmployeeData(EmployeeData employee);
    EmployeeData employeeDataToEmployeeDataDto(EmployeeDataDto employeeDataDto);
}
