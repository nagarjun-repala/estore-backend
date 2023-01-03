package com.nagarjun.estorebackend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

import com.nagarjun.estorebackend.entity.CartDetails;

public interface CartDetailsRepository extends CrudRepository<CartDetails, Long> {

    @Transactional
    void deleteByCartIdAndProductId(Long cartId, Long productId);
    CartDetails findByCartIdAndProductId(Long cartId, Long productId);
    Optional<List<CartDetails>> findAllByCartId(Long cartId);

    
}
