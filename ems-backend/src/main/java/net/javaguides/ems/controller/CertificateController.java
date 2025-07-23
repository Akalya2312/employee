package net.javaguides.ems.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.service.Certificateservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {

    private final Certificateservice certificateService;

    @PostMapping
    public ResponseEntity<Certificatedto> createCertificate(@RequestBody Certificatedto certificateDto) {
        Certificatedto savedCertificate = certificateService.createCertificate(certificateDto);
        return new ResponseEntity<>(savedCertificate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Certificatedto>> getAllCertificates() {
        List<Certificatedto> certificates = certificateService.getAllCertificates();
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificatedto> getCertificateById(@PathVariable Long id) {
        Certificatedto certificate = certificateService.getCertificateById(id);
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

}