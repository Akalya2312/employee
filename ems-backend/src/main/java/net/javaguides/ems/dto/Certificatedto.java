package net.javaguides.ems.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.javaguides.ems.entity.Employee;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificatedto
{
    private List<Long> cid;
    private List<String> cname;
    @JsonManagedReference
    private List<Employee>employees;
}