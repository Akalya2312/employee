package net.javaguides.ems.controller;
import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Addressdto;
import net.javaguides.ems.service.Addressservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor

public class Addresscontroller {

    private final Addressservice addressService;


    @PostMapping
    public ResponseEntity<Addressdto> create(@RequestBody Addressdto dto) {
        return ResponseEntity.ok(addressService.createAddress(dto));
    }


    @GetMapping
    public ResponseEntity<List<Addressdto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("{id}")
    public ResponseEntity<Addressdto> getByAddressId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(addressService.getByAddressId(id));
    }

}
