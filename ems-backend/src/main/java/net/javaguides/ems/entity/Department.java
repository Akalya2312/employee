package net.javaguides.ems.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long deptid;
    @Column(nullable=false)
    private String deptname;
    @OneToMany(mappedBy="department", cascade=CascadeType.ALL)
    private List<Employee> employees=new ArrayList<>();
}