package net.employeeproject.ems.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.employeeproject.ems.entity.Employee;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addressdto {
    private Long addrid;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    @JsonManagedReference

    private Employee employee;
}
