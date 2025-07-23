package net.javaguides.ems.repository;

import net.javaguides.ems.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Addressrepo extends JpaRepository<Address, Long> {

}
