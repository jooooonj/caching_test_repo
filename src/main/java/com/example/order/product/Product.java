package com.example.order.product;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "stock", nullable = false)
    private int stock;

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void removeStock(int quantity) throws IllegalAccessException {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new IllegalAccessException("need more stock");
        }
        this.stock = restStock;
    }
}

