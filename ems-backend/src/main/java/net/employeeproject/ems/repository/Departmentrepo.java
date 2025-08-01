package net.employeeproject.ems.repository;
import net.employeeproject.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Departmentrepo extends JpaRepository<Department,Long>{

    Optional<Department> findByDeptname(String deptname);

}