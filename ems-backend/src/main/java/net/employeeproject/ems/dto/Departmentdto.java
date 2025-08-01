package net.employeeproject.ems.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.employeeproject.ems.entity.Employee;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departmentdto {
    private Long deptid;
    private String deptname;
    @JsonManagedReference
    private List<Employee> employees;
}
