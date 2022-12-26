package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import com.nagarjun.estorebackend.entity.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

    Optional <List<Address>> findAllByUserId(Long userId);
    
}
