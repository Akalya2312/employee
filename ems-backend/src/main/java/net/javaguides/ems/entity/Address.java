package net.javaguides.ems.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long addrid;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zipcode;
    @OneToOne(mappedBy="address")
    private Employee employee;

}
