package net.employeeproject.ems.dto;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employeedto {
    private Long empid;
    private String empname;
    private Long phno;
    private String email;
    private String password;
    private Long deptid;
    private String deptname;
    private Long addrid;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private List<Long> cid;
    private List<String> cname;
}
