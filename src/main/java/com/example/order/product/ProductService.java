package com.example.order.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
//    private final StringRedisTemplate stringRedisTemplate;
    private final ProductDetailService productDetailService;
    private final RedisTemplate<Long, Product> redistemplate;

    @Cacheable(value = "products", key = "0")
    public List<Product> findAll() {
        System.out.print("========================================findAll");
        return productRepository.findAll();
    }

    //어노테이션으로 캐싱처리 테스트
//    @Cacheable(value = "product", key = "#productId")
    public Product findProductWithAnotation(Long productId) {
        System.out.print("========================================findProduct");
//        ValueOperations<Long, Product> longProductValueOperations = redistemplate.opsForValue();
//        longProductValueOperations.set(product.getId(), product);
        return productDetailService.findProductWithAnotationDepth1(productId);
    }

    //캐시 수정
    @Transactional
    @CachePut(value = "product", key = "#productId")
    public Product modifyStock(int stock, Long productId) {
        System.out.print("========================================modify-stock");
        Product product = productRepository.findById(1L).orElse(null);
        product.addStock(stock);
        return product;
    }

    @Transactional
    @CacheEvict(value = "product", key = "#productId", allEntries = true)
    public void deleteProduct(Long productId) {
        System.out.print("========================================delete");
        productRepository.deleteById(productId);
    }

    @Transactional(readOnly = true)
    public Product getAllByPage(int offset) {
    }
}
