package net.employeeproject.ems.mapper;
import net.employeeproject.ems.dto.Employeedto;
import net.employeeproject.ems.entity.Employee;
import net.employeeproject.ems.entity.Certificate;

import java.util.ArrayList;
import java.util.List;

public  class EmployeeMapper {

    public static Employeedto mapToEmployeedto(Employee employee) {
        List<Long> certId = new ArrayList<>();
        List<String> certName = new ArrayList<>();
        if (employee.getCertificates() != null) {
            for (Certificate cert : employee.getCertificates()) {
                certId.add(cert.getCid());
                certName.add(cert.getCname());
            }
        }
        return new Employeedto(
                employee.getEmpid(),
                employee.getEmpname(),
                employee.getPhno(),
                employee.getEmail(),
                employee.getPassword(),
                employee.getDepartment() != null ? employee.getDepartment().getDeptid() : null,
                employee.getDepartment() != null ? employee.getDepartment().getDeptname() : null,
                employee.getAddress() != null ? employee.getAddress().getAddrid() : null,
                employee.getAddress() != null ? employee.getAddress().getStreet() : null,
                employee.getAddress() != null ? employee.getAddress().getCity() : null,
                employee.getAddress() != null ? employee.getAddress().getState() : null,
                employee.getAddress() != null ? employee.getAddress().getZipcode() : null,
                certId,
                certName
        );
    }

    public static Employee mapToEmployee(Employeedto employeedto) {
        return new Employee(
                employeedto.getEmpid(),
                employeedto.getEmpname(),
                employeedto.getPhno(),
                employeedto.getEmail(),
                employeedto.getPassword(),
                null,
                null,
                null
        );
    }

    public static void updateEmployeeFromDto(Employee employee, Employeedto employeeDto) {
        employee.setEmpname(employeeDto.getEmpname());
        employee.setPhno(employeeDto.getPhno());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());
    }
}
