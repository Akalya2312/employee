package net.employeeproject.ems.service;

import net.employeeproject.ems.dto.Certificatedto;

import java.util.List;

public interface Certificateservice {
  Certificatedto createCertificate(Certificatedto cdto);
   List<Certificatedto> getAllCertificates();

  Certificatedto getCertificateById(Long id);
}