package com.nagarjun.estorebackend.repository;

import org.springframework.data.repository.CrudRepository;

import com.nagarjun.estorebackend.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
