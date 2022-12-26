package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.AddressNotFoundException;
import com.nagarjun.estorebackend.repository.AddressRepository;
import com.nagarjun.estorebackend.repository.UserRepository;

public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address getAddress(Long addressId) {
        Optional<Address> addressEntity = addressRepository.findById(addressId);
        if(addressEntity.isEmpty()) throw new AddressNotFoundException(addressId);
        return addressEntity.get();
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
        
    }

    @Override
    public Address createAddress(Address address, Long userId) {
        User user = userRepository.findById(userId).get();
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        Optional<List<Address>> addressEntity = addressRepository.findAllByUserId(userId);
        if(addressEntity.isEmpty()) throw new AddressNotFoundException(userId);
        return addressEntity.get();
    }
    
}
