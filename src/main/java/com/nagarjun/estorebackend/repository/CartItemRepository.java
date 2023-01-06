package com.nagarjun.estorebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
        
    Optional<CartItem> findByCartIdAndProductId(Long userId, Long productId);
}
