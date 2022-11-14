package com.nagarjun.estorebackend.Repository;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
    
}
