package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
 
}
