package com.nagarjun.estorebackend.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.nagarjun.estorebackend.entity.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
        
    Optional<CartItem> findByCartIdAndProductId(Long userId, Long productId);
    @Transactional
    void deleteByCartIdAndProductId(Long cartId, Long productId);
    Optional <List<CartItem>> findByCartId(Long cartId);
}
