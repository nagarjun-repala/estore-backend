package com.nagarjun.estorebackend.Repository;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
