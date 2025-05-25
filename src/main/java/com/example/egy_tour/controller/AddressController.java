package com.example.egy_tour.controller;

import com.example.egy_tour.dto.CreateAddressDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public Address createAddress(@Valid @RequestBody CreateAddressDTO createAddressDTO) {
        return addressService.createAddress(createAddressDTO);
    }
}
