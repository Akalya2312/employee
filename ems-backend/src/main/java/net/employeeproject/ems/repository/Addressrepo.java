package net.employeeproject.ems.repository;

import net.employeeproject.ems.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Addressrepo extends JpaRepository<Address, Long> {

}
