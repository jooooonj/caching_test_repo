package com.example.order.orders.entity;

import lombok.Data;

@Data
public class OrderRequest {
    Long productId;
    int quantity;
}
