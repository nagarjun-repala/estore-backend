package com.nagarjun.estorebackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

    // @Transactional
    // void deleteByCartIdAndProductId(Long cartId, Long productId);    
}
