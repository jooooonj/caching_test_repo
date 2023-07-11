package com.example.order.initData;
import com.example.order.orders.repository.OrderRepository;
import com.example.order.orders.service.OrderService;
import com.example.order.product.Product;
import com.example.order.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean
    CommandLineRunner initData(
            OrderService orderService,
            OrderRepository orderRepository,
            ProductRepository productRepository
    ) {
        return args -> {

            for(int i=0; i<100; i++){
                Product product = Product.builder().price(100+i).stock(100+i).name("상품").build();
                productRepository.save(product);
            }

        };
    }
}
