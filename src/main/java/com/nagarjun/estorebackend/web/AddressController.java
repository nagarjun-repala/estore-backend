package com.nagarjun.estorebackend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarjun.estorebackend.entity.Address;
import com.nagarjun.estorebackend.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable Long addressId) {

        return new ResponseEntity<>(addressService.getAddress(addressId), HttpStatus.OK);
    }
    
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long addressId) {

        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@AuthenticationPrincipal String username, @RequestBody Address address){

        return new ResponseEntity<>(addressService.createAddress(address, username), HttpStatus.CREATED);
    }

    @GetMapping("/getAddresses")
    public ResponseEntity<List<Address>> getAddressesByUserId(@AuthenticationPrincipal String username){
        return new ResponseEntity<>(addressService.getAddresses(username), HttpStatus.OK);
    }
    
}
