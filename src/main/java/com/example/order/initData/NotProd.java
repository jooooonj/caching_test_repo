package com.example.order.initData;
import com.example.order.orders.repository.OrderRepository;
import com.example.order.orders.service.OrderService;
import com.example.order.product.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean
    CommandLineRunner initData(
            OrderService orderService,
            OrderRepository orderRepository
    ) {
        return args -> {
            Product product = Product.builder().price(100).stock(100).name("상품").build();


        };
    }
}
