package net.employeeproject.ems.repository;
import net.employeeproject.ems.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface Employeerepo extends JpaRepository <Employee,Long> {
    Optional<Employee> findByEmail(String email);

}