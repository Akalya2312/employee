package net.javaguides.ems.service;

import net.javaguides.ems.dto.Addressdto;

import java.util.List;

public interface Addressservice {
    Addressdto createAddress(Addressdto dto);
    List<Addressdto> getAllAddresses();
    Addressdto getByAddressId(Long id);
}
