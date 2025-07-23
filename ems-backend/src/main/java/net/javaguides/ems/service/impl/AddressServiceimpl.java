package net.javaguides.ems.service.impl;
import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Addressdto;
import net.javaguides.ems.dto.Departmentdto;
import net.javaguides.ems.entity.Address;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.mapper.AddressMapper;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repository.Addressrepo;
import net.javaguides.ems.service.Addressservice;
import org.springframework.beans.factory.annotation.Autowired;
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
