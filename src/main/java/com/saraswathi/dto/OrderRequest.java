package com.saraswathi.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private Long productId;   // which product user wants
    private int quantity;     // how many
}