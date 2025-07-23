package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.Certificatedto;
import net.javaguides.ems.entity.Certificate;
import net.javaguides.ems.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CertificateMapper {

    public static Certificatedto mapToCertificatedto(List<Certificate> certs) {
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (Certificate cert : certs) {
            ids.add(cert.getCid());
            names.add(cert.getCname());}
            List<Employee> employees = certs.stream()
                    .flatMap(c -> c.getEmployees().stream())
                    .distinct()
                    .collect(Collectors.toList());


        return new Certificatedto(ids, names,employees);
    }
    public static List<Certificatedto> mapToCertificatedtoList(List<Certificate> certs) {
        List<Certificatedto> dtoList = new ArrayList<>();
        for (Certificate cert : certs) {
            dtoList.add(mapToCertificatedto(List.of(cert)));
        }
        return dtoList;
    }

    public static List<Certificate> mapToCertificateList(Certificatedto dto) {
        List<Certificate> certs = new ArrayList<>();
        List<Long> ids = dto.getCid();
        List<String> names = dto.getCname();

        if (ids != null && names != null && ids.size() == names.size()) {
            for (int i = 0; i < ids.size(); i++) {
                Certificate cert = new Certificate();
                cert.setCid(ids.get(i));
                cert.setCname(names.get(i));
                certs.add(cert);
            }
        }

        return certs;
    }
}

