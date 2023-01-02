package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.CartDetails;

public interface CartDetailsRepository extends CrudRepository<CartDetails, Long> {
    
}
