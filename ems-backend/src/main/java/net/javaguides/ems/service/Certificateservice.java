package net.javaguides.ems.service;

import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.entity.Certificate;

import java.util.List;

public interface Certificateservice {
  Certificatedto createCertificate(Certificatedto cdto);
   List<Certificatedto> getAllCertificates();

  Certificatedto getCertificateById(Long id);
}