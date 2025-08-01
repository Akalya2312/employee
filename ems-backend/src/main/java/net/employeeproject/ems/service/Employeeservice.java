package net.employeeproject.ems.service;
import net.employeeproject.ems.dto.Certificatedto;
import net.employeeproject.ems.dto.Employeedto;
import java.util.List;
public interface Employeeservice {
    Employeedto createEmployee(Employeedto employeeDto);
    Employeedto getEmployeeById(Long employeeId);
    List<Employeedto> getAllEmployees();
    Employeedto updateEmployee(Long employeeId,Employeedto updateEmployee);
    void deleteEmployee(Long employeeId);
    Employeedto addCertificatesToEmployee(Long empId, List<Certificatedto> certificateDtos);

}
