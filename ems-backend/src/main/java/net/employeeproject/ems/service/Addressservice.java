package net.employeeproject.ems.service;

import net.employeeproject.ems.dto.Addressdto;

import java.util.List;

public interface Addressservice {
    Addressdto createAddress(Addressdto dto);
    List<Addressdto> getAllAddresses();
    Addressdto getByAddressId(Long id);
}
