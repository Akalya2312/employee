package net.javaguides.ems.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;

    @Column(name = "emp_name", nullable = false)
    private String empname;

    @Column(name = "phone_number", nullable = false)
    private Long phno;

    @Column(name = "email_id", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptid")
    @JsonBackReference
    @JsonIgnore
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addrid")
    @JsonBackReference
    @JsonIgnore
    private Address address;

    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name = "employee_certificate",
            joinColumns = @JoinColumn(name = "empid"),
            inverseJoinColumns = @JoinColumn(name = "cid")
    )
    @JsonBackReference
    private List<Certificate> certificates = new ArrayList<>();
}
