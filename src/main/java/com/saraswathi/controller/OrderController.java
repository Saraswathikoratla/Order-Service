package com.saraswathi.controller;

import com.saraswathi.dto.OrderRequest;
import com.saraswathi.dto.Product;
import com.saraswathi.service.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @GetMapping("/all")
    public String getOrders(
            @RequestHeader("X-User") String user,
            @RequestHeader(value = "X-Role", required = false) String role) {
        System.out.println("role.."+role);
        log.info("role  {}     : ",role);
        return "Orders for user: " + user + " | Role: " + role;
    }
    @Autowired
    private ProductClient productClient;

    @PostMapping
    public String placeOrder(
            @RequestHeader("X-User") String user,
            @RequestBody OrderRequest request) {

        Product product = productClient.getProductById(request.getProductId());

        if (product == null) {
            return "Product not found";
        }

        if (product.getQuantity() < request.getQuantity()) {
            return "Insufficient stock";
        }

        return "Order placed by " + user + " for " + product.getName();
    }

}