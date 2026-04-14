package com.saraswathi.service;

import com.saraswathi.dto.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackProduct")
    public Product getProductById(Long productId) {
        return productClient.getProductById(productId);
    }

    // 🔥 fallback
    public Product fallbackProduct(Long productId, Exception ex) {
        System.out.println("Fallback triggered: " + ex.getMessage());

        Product product = new Product();
        product.setId(productId);
        product.setName("Service Down Product");
        product.setQuantity(0);
        return product;
    }
}