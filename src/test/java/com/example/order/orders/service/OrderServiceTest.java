package com.example.order.orders.service;

import com.example.order.orders.entity.Order;
import com.example.order.orders.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("100명이 동시에 상품을 구매한다면 ?")
    public void t01() throws InterruptedException {
        int threadCount = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);//32개 스레드 생성
        CountDownLatch latch = new CountDownLatch(threadCount); //스레드 완료 대기를 위해

        for(int i=0; i<threadCount; i++){
            executorService.submit(()->{
                try{
                    orderService.cancel(); //문제의 메서드 호출
                } finally {
                    latch.countDown(); //완료되었음을 알림
                }
            });
        }

        latch.await();
        Order order = orderRepository.findById(1L).orElseThrow();

        //100 -(1*100) = 0을 예상

    }
}
