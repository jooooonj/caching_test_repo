package com.example.order.orders.service;

import com.example.order.orders.entity.Order;
import com.example.order.orders.entity.OrderItem;
import com.example.order.orders.entity.OrderRequest;
import com.example.order.orders.repository.OrderRepository;
import com.example.order.product.Product;
import com.example.order.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //단건 주문
    public void orderOne(OrderRequest orderRequest) {

        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow();

        if (product.getStock() < orderRequest.getQuantity()){
            System.out.println("주문실패");
            throw new RuntimeException("stock is not enough");   // 임시. 나중에 사용자 exception 널을까말까 생각
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(OrderItem.builder().count(orderRequest.getQuantity()).product(product).build());

        Order order = Order.createOrder(orderItemList);
        orderRepository.save(order);
    }

    public void cancel() {

    }
}
