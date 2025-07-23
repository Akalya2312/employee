package net.javaguides.ems.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.javaguides.ems.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departmentdto {
    private Long deptid;
    private String deptname;
    @JsonManagedReference

    private List<Employee> employees;
}
