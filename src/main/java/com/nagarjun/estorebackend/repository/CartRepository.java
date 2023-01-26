package com.nagarjun.estorebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
    Optional<Cart> findByUsername(String username);
 
}
