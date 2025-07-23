package net.javaguides.ems.service.impl;

import net.javaguides.ems.dto.Employeedto;
import net.javaguides.ems.entity.Address;
import net.javaguides.ems.entity.Certificate;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.Employeevalidator;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.Addressrepo;
import net.javaguides.ems.repository.Certificaterepo;
import net.javaguides.ems.repository.Departmentrepo;
import net.javaguides.ems.repository.Employeerepo;
import net.javaguides.ems.service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceimpl implements Employeeservice {

    private final Employeerepo employeeRepository;
    private final Departmentrepo departmentrepo;
    private final Addressrepo addressrepo;
    private final Certificaterepo certificaterepo;

    @Autowired
    public EmployeeServiceimpl(Employeerepo employeeRepository,
                               Departmentrepo departmentrepo,
                               Addressrepo addressrepo,
                               Certificaterepo certificaterepo) {
        this.employeeRepository = employeeRepository;
        this.departmentrepo = departmentrepo;
        this.addressrepo = addressrepo;
        this.certificaterepo = certificaterepo;
    }

    @Override
    public Employeedto createEmployee(Employeedto employeeDto) {

        Employeevalidator.validate(employeeDto, employeeRepository, false, null);
        Department dept = departmentrepo.findByDeptname(employeeDto.getDeptname())
                .orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setDeptname(employeeDto.getDeptname());
                    return departmentrepo.save(newDept);
                });

        Address address = new Address();
        address.setStreet(employeeDto.getStreet());
        address.setCity(employeeDto.getCity());
        address.setState(employeeDto.getState());
        address.setZipcode(employeeDto.getZipcode());
        address = addressrepo.save(address);
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setDepartment(dept);
        employee.setAddress(address);


        if (employeeDto.getCname() != null && !employeeDto.getCname().isEmpty()) {
            List<Certificate> certs = new ArrayList<>();

            for (String cname : employeeDto.getCname()) {
                Certificate cert = certificaterepo.findByCname(cname)
                        .orElseGet(() -> certificaterepo.save(new Certificate(cname)));
                certs.add(cert);
            }


            employee.setCertificates(certs);
        }

        // Save and return
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeedto(savedEmployee);
    }

    @Override
    public Employeedto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeedto(employee);
    }

    @Override
    public List<Employeedto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeedto)
                .collect(Collectors.toList());
    }

    @Override
    public Employeedto updateEmployee(Long employeeId, Employeedto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));

        Employeevalidator.validate(updatedEmployee, employeeRepository, true, employeeId);
        EmployeeMapper.updateEmployeeFromDto(employee, updatedEmployee);
        Department dept = departmentrepo.findByDeptname(updatedEmployee.getDeptname())
                .orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setDeptname(updatedEmployee.getDeptname());
                    return departmentrepo.save(newDept);
                });
        employee.setDepartment(dept);

        Address addr = employee.getAddress();
        if (addr == null) {
            addr = new Address();
        }
        addr.setStreet(updatedEmployee.getStreet());
        addr.setCity(updatedEmployee.getCity());
        addr.setState(updatedEmployee.getState());
        addr.setZipcode(updatedEmployee.getZipcode());
        addr = addressrepo.save(addr);
        employee.setAddress(addr);


        if (updatedEmployee.getCname() != null && !updatedEmployee.getCname().isEmpty()) {
            List<Certificate> updatedCerts = new ArrayList<>();
            for (String cname : updatedEmployee.getCname()) {
                Certificate cert = certificaterepo.findByCname(cname)
                        .orElseGet(() -> certificaterepo.save(new Certificate(cname)));
                updatedCerts.add(cert);
            }
            employee.setCertificates(updatedCerts);
        }


        Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeedto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));
        Department dept=employee.getDepartment();
        employee.getCertificates().clear();
        employeeRepository.deleteById(employeeId);
    }
}
