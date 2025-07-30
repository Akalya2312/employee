package net.javaguides.ems.controller;

import lombok.RequiredArgsConstructor;
import net.javaguides.ems.dto.Addressdto;
import net.javaguides.ems.payload.ApiResponse;
import net.javaguides.ems.service.Addressservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class Addresscontroller {

    private final Addressservice addressService;
    public final String ADDRESS_CREATED_SUCCESSFULLY="Address created successfully";
    public final String ADDRESS_FETCHED_SUCCESSFULLY="Address fetched successfully";
    public final String SPECIFIC_ADDRESS_FETCHED_SUCCESSFULLY="Specific Address fetched successfully";


    @PostMapping
    public ResponseEntity<ApiResponse<Addressdto>> create(@RequestBody Addressdto dto) {
        Addressdto created = addressService.createAddress(dto);
        ApiResponse<Addressdto> response = new ApiResponse<>(
                ADDRESS_CREATED_SUCCESSFULLY,
                created,
                true,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Addressdto>>> getAllAddresses() {
        List<Addressdto> list = addressService.getAllAddresses();
        ApiResponse<List<Addressdto>> response = new ApiResponse<>(
                ADDRESS_FETCHED_SUCCESSFULLY,
                list,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Addressdto>> getByAddressId(@PathVariable("id") Long id) {
        Addressdto dto = addressService.getByAddressId(id);
        ApiResponse<Addressdto> response = new ApiResponse<>(
                SPECIFIC_ADDRESS_FETCHED_SUCCESSFULLY,
                dto,
                true,
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }
}
