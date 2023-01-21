package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarjun.estorebackend.entity.Product;
import com.nagarjun.estorebackend.exception.ProductNotFoundException;
import com.nagarjun.estorebackend.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(Long productId) {

        Optional<Product> productEntity = productRepository.findById(productId);
        if(productEntity.isEmpty()) throw new ProductNotFoundException(productId);
        return productEntity.get();
    }

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {     
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Optional<Product> productEntity = productRepository.findById(productId);
        if(productEntity.isEmpty()) throw new ProductNotFoundException(productId);
        Product updateProduct = productEntity.get();
        updateProduct.setDescription(product.getDescription());
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        return productRepository.save(updateProduct);
    }

    @Override
    public List<Product> getProducts() {

        return (List<Product>) productRepository.findAll();
    }

    static Product unwrapProduct(Optional<Product> entity, Long id) {

        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();

    }
   
}
