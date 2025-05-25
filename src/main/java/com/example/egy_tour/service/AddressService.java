package com.example.egy_tour.service;

import com.example.egy_tour.dto.CreateAddressDTO;
import com.example.egy_tour.model.Address;
import com.example.egy_tour.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(CreateAddressDTO createAddressDTO) {
        Address realAddress = new Address(createAddressDTO.getName());
        if (createAddressDTO.getParentName() != null && !createAddressDTO.getParentName().isEmpty()) {
            Address parent = addressRepository.findAddressByName(createAddressDTO.getParentName());
            if (parent != null)  // Getting Address from the parent Name
                realAddress.setParent(parent);
            else {
                Address parentAddress = new Address(createAddressDTO.getName());
                addressRepository.save(parentAddress);
                realAddress.setParent(parentAddress);
            }

        }
        return addressRepository.save(realAddress);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
