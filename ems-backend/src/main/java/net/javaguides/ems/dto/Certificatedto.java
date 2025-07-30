package net.javaguides.ems.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.javaguides.ems.entity.Employee;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificatedto
{
    private List<Long> cid;
    private List<String> cname;
    //@JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Employee>employees;
}