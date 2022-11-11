package com.nagarjun.estorebackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarjun.estorebackend.Repository.ProductRepository;
import com.nagarjun.estorebackend.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(Long productId) {

        Optional<Product> productEntity = productRepository.findById(productId);
        
        return unwrapProduct(productEntity, productId);
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

        Optional<Product> getProduct = productRepository.findById(productId);
        Product unwrappedProduct = unwrapProduct(getProduct, productId);
        unwrappedProduct.setDescription(product.getDescription());
        unwrappedProduct.setName(product.getName());
        unwrappedProduct.setPrice(product.getPrice());

        return productRepository.save(unwrappedProduct);

    }

    @Override
    public List<Product> getProducts() {

        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product addProductToUser(Long userId, Long productId) {

        return new Product();
    }

    static Product unwrapProduct(Optional<Product> entity, Long id) {

        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();

    }
    
}
