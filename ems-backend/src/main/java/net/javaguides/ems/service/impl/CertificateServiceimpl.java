package net.javaguides.ems.service.impl;

import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.entity.Certificate;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.CertificateMapper;
import net.javaguides.ems.repository.Certificaterepo;
import net.javaguides.ems.repository.Employeerepo;
import net.javaguides.ems.service.Certificateservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceimpl implements Certificateservice {

    private final Certificaterepo certificateRepository;
    private final Employeerepo employeerepo;

    // ✅ Create and save multiple certificates
    @Override
    public Certificatedto createCertificate(Certificatedto cdto) {
        List<Certificate> certificates = CertificateMapper.mapToCertificateList(cdto);
        List<Certificate> savedCertificates = certificateRepository.saveAll(certificates);
        return CertificateMapper.mapToCertificatedto(savedCertificates);
    }

    @Override
    public List<Certificatedto> getAllCertificates() {
        List<Certificate> certificates = certificateRepository.findAll();
        return CertificateMapper.mapToCertificatedtoList(certificates);
    }

    @Override
    public Certificatedto getCertificateById(Long cid) {
        Certificate certificate = certificateRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Certificate not found with ID: " + cid));
        return CertificateMapper.mapToCertificatedto(List.of(certificate)); // Wrap single cert in list
    }

}
