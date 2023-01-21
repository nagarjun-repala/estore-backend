package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.nagarjun.estorebackend.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

    List<Address> findAllByUserId(Long userId);
    
}
