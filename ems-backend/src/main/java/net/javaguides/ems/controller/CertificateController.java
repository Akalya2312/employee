package net.javaguides.ems.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.entity.Certificate;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.payload.ApiResponse;
import net.javaguides.ems.repository.Certificaterepo;
import net.javaguides.ems.repository.Employeerepo;
import net.javaguides.ems.security.JwtUtil;
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
    private final JwtUtil jwtutil;
    private final Employeerepo employeerepo;
    private final Certificaterepo certificaterepo;
    public final String CERTIFICATE_CREATED_SUCCESSFULLY="Certificate Created Successfully";
    public final String CERTIFICATE_FETCHED_SUCCESSFULLY="Certificate fetched Successfully";
    public final String SPECIFIC_CERTIFICATE_FETCHED_SUCCESSFULLY="Specific Certificate fetched Successfully";

    @PostMapping
    public ResponseEntity<ApiResponse<Certificatedto>> createCertificate(@RequestBody Certificatedto certificateDto) {
        Certificatedto savedCertificate = certificateService.createCertificate(certificateDto);
        ApiResponse<Certificatedto> response = new ApiResponse<>(
                CERTIFICATE_CREATED_SUCCESSFULLY,
                savedCertificate,
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Certificatedto>>> getAllCertificates() {
        List<Certificatedto> certificates = certificateService.getAllCertificates();
        ApiResponse<List<Certificatedto>> response = new ApiResponse<>(
                CERTIFICATE_FETCHED_SUCCESSFULLY,
                certificates,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Certificatedto>> getCertificateById(@PathVariable Long id) {
        Certificatedto certificate = certificateService.getCertificateById(id);
        ApiResponse<Certificatedto> response = new ApiResponse<>(
                SPECIFIC_CERTIFICATE_FETCHED_SUCCESSFULLY,
                certificate,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }



}
