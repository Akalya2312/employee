package net.employeeproject.ems.entity;
import lombok.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cid;
    @Column(nullable=false)
    private String cname;
    @ManyToMany(mappedBy="certificates")
    private List<Employee> employees= new ArrayList<>();
    public Certificate(String cname) {
        this .cname = cname;
    }
}
