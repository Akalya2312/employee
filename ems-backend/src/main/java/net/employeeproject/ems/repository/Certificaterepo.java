package net.employeeproject.ems.repository;

import net.employeeproject.ems.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Certificaterepo  extends JpaRepository<Certificate,Long> {
    Optional<Certificate> findByCname(String cname);

}
