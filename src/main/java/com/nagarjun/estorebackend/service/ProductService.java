package com.nagarjun.estorebackend.service;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import com.nagarjun.estorebackend.entity.Product;

public interface ProductService {

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    Product getProduct(Long productId);

    @PreAuthorize("hasRole('ADMIN')")
    Product createProduct(Product product);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteProduct(Long productId);

    @PreAuthorize("hasRole('ADMIN')")
    Product updateProduct(Long productId, Product product);

    @PreAuthorize("hasRole('ADMIN')")
    List<Product> getProducts();
    
}
