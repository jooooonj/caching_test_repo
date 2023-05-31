package com.example.order.orders.entity;

import com.example.order.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "count", nullable = false)
    private int count;

    public void connectOrder(Order order) {
        this.order = order;
    }
    public void cancel() {
        this.product.addStock(count);
    }

    public int getTotalPrice() {
        return count * getProduct().getPrice();
    }
}
