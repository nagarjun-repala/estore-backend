package com.nagarjun.estorebackend.service;

import java.util.List;
import com.nagarjun.estorebackend.entity.Product;

public interface ProductService {

    Product getProduct(Long productId);
    Product createProduct(Product product);
    void deleteProduct(Long productId);
    void updateProduct(Long productId, Product product);
    Product addProductToUser(Long userId);
    List<Product> getProducts();

    
}
