package com.nagarjun.estorebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{

    Optional <List<Review>> findByProductId(Long productId);
    List<Review> findByUserUsername(String username);
    
}
