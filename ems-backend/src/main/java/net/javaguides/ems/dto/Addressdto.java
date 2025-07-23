package net.javaguides.ems.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.javaguides.ems.entity.Employee;

@Getter
@Setter
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
