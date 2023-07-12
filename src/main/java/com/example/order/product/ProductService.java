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
public class ProductService {
    private final ProductRepository productRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Cacheable(value = "products", key = "0")
    public List<Product> findAll() {
        System.out.print("========================================findAll");
        return productRepository.findAll();
    }

    //어노테이션으로 캐싱처리 테스트
//    @Cacheable(value = "product", key = "#productId")
    public Product findProductWithAnotation(Long productId) {
        System.out.print("========================================findProduct");
        return findProductWithAnotationDepth1(productId);
//        return productRepository.findById(productId).orElse(null);
    }

    @Cacheable(value = "product", key = "#productId")
    public Product findProductWithAnotationDepth1(Long productId) {
        System.out.print("========================================depth1");
        return productRepository.findById(productId).orElse(null);
    }


    //직접 레디스에 저장한 후 테스트
    public Product findProductWithRedisTemplate(Long productId) {
        System.out.print("========================================findProduct");
        return productRepository.findById(productId).orElse(null);
    }

    @Transactional
    public void modifyStock(int stock) {
        System.out.print("========================================modify-stock");
        Product product = productRepository.findById(1L).orElse(null);
        product.addStock(stock);
    }


}
