package com.nagarjun.estorebackend.service;

import java.util.List;

import com.nagarjun.estorebackend.entity.Address;

public interface AddressService {

    Address getAddress(Long addressId);
    void deleteAddress(Long addressId);
    Address createAddress(Address address, String username);
    List<Address> getAddresses(Long userId);
    List<Address> getAddresses(String username);

    
}
