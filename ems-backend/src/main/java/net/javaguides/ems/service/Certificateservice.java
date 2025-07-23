package net.javaguides.ems.service;

import net.javaguides.ems.dto.Certificatedto;

import java.util.List;

public interface Certificateservice {
  Certificatedto createCertificate(Certificatedto cdto);
   List<Certificatedto> getAllCertificates();

  Certificatedto getCertificateById(Long id);
}