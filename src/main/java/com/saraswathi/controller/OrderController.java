package com.saraswathi.controller;

import com.saraswathi.dto.OrderRequest;
import com.saraswathi.dto.OrderResponse;
import com.saraswathi.dto.Product;
import com.saraswathi.service.OrderService;
import com.saraswathi.service.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse placeOrder(
            @RequestHeader("X-User") String user,
            @RequestBody OrderRequest request) {

        Product product = service.getProductById(request.getProductId());

        OrderResponse response = new OrderResponse();
        response.setUser(user);
        response.setProduct(product);

        // 🔥 Handle fallback case
        if (product == null || product.getQuantity() == 0) {
            response.setMessage("Product service unavailable");
            return response;
        }

        // 🔥 Business logic
        if (product.getQuantity() < request.getQuantity()) {
            response.setMessage("Insufficient stock");
            return response;
        }

        response.setMessage("Order placed successfully");
        return response;
    }
}