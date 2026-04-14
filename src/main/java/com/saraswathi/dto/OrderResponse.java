package com.saraswathi.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private String message;
    private Product product;
    private String user;
}