package net.employeeproject.ems.service.impl;
import lombok.RequiredArgsConstructor;
import net.employeeproject.ems.dto.Addressdto;
import net.employeeproject.ems.entity.Address;
import net.employeeproject.ems.mapper.AddressMapper;
import net.employeeproject.ems.repository.Addressrepo;
import net.employeeproject.ems.service.Addressservice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceimpl implements Addressservice {


    private final Addressrepo addressRepo;

    @Override
    public Addressdto createAddress(Addressdto dto) {
        Address address = AddressMapper.mapToAddress(dto);
        Address saved = addressRepo.save(address);
        return AddressMapper.mapToAddressdto(saved);
    }


    @Override
    public List<Addressdto> getAllAddresses() {
        List<Address> addresses = addressRepo.findAll();
        return addresses.stream()
                .map(AddressMapper::mapToAddressdto)
                .toList();
    }
    @Override
    public Addressdto getByAddressId(Long id) {
        Address addr = addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        return AddressMapper.mapToAddressdto(addr);
    }


}
