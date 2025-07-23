package net.javaguides.ems.service;
import net.javaguides.ems.dto.Employeedto;
import java.util.List;
public interface Employeeservice {
    Employeedto createEmployee(Employeedto employeeDto);
    Employeedto getEmployeeById(Long employeeId);
    List<Employeedto> getAllEmployees();
    Employeedto updateEmployee(Long employeeId,Employeedto updateEmployee);
    void deleteEmployee(Long employeeId);
}
