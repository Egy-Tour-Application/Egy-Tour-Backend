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
        Address realAddress = getAddressByName(createAddressDTO.getName());
        if (realAddress == null) {
            realAddress = new Address(createAddressDTO.getName());
            if (createAddressDTO.getParentName() != null && !createAddressDTO.getParentName().isEmpty()) {
                Address parent = getAddressByName(createAddressDTO.getParentName());
                if (parent != null)  // Getting Address from the parent Name
                    realAddress.setParent(parent);
                else {
                    Address parentAddress = new Address(createAddressDTO.getParentName());
                    addressRepository.save(parentAddress);
                    realAddress.setParent(parentAddress);
                }
            }
            return addressRepository.save(realAddress);
        } else {
            return realAddress;
        }

    }

    public Address getAddressByName(String name) {
        return addressRepository.findAddressByName(name);
    }
}
