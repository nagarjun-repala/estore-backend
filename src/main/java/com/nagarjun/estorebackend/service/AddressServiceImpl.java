package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.nagarjun.estorebackend.Constants;
import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.entity.User;
import com.nagarjun.estorebackend.exception.ResourceNotFoundException;
import com.nagarjun.estorebackend.repository.AddressRepository;
import com.nagarjun.estorebackend.repository.UserRepository;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address getAddress(Long addressId) {
        Optional<Address> addressEntity = addressRepository.findById(addressId);
        if(addressEntity.isEmpty()) throw new ResourceNotFoundException(addressId, Constants.ADDRESS);
        return addressEntity.get();
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
        
    }

    @Override
    public Address createAddress(Address address, Long userId) {
        // TODO Auto-generated method stub        
        User user = userRepository.findById(userId).get();
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        List<Address> addresses = addressRepository.findAllByUserId(userId).get();
        if(addresses.isEmpty()) throw new ResourceNotFoundException(userId, Constants.USER);
        return addresses;
    }

    @Override
    public Address createAddress(Address address, String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Address> getAddressesByUserId(String username) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
