package com.example.order.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailService {
    private final ProductRepository productRepository;
    @Cacheable(value = "product", key = "#productId")
    public Product findProductWithAnotationDepth1(Long productId) {
        System.out.print("========================================depth1");
        return productRepository.findById(productId).orElse(null);
    }
}
