package com.nagarjun.estorebackend.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.nagarjun.estorebackend.entity.Address;

public interface AddressService {

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostAuthorize("(returnObject.user.username == principal.username)")
    Address getAddress(Long addressId);

    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND @accessService.hasAccessToAddress(#addressId, principal.userId))")
    void deleteAddress(Long addressId);
    
    Address createAddress(Address address, String username);
    List<Address> getAddresses(Long userId);
    List<Address> getAddresses(String username);

    
}
