package com.example.order.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        System.out.print("========================================findAll");
        return productRepository.findAll();
    }


    public Product findProduct() {
        System.out.print("========================================findProduct");
        return productRepository.findById(1L).orElse(null);
    }

    @Transactional
    public void modifyStock(int stock) {
        System.out.print("========================================modify-stock");
        Product product = productRepository.findById(1L).orElse(null);
        product.addStock(stock);
    }
}
