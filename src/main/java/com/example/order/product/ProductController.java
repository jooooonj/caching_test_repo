package com.example.order.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/redis/all")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/redis/normal")
    public Product getOneWithAnotation(@RequestParam("productId") Long productId) {
        return productService.findProductWithAnotation(productId);
    }

    @GetMapping("/redis/page")
    public Product getAllByPage(@RequestParam("offset") int offset) {
        return productService.getAllByPage(offset);
    }

//    @GetMapping("/redis/v2/test")
//    public Product getOneWithRedisTemplate(@RequestParam("productId") Long productId) {
//        return productService.findProductWithRedisTemplate(productId);
//    }


    @PostMapping("/redis/modify")
    public void modify(@RequestParam("stock") int stock) {
        Long productId = 1L;
        productService.modifyStock(stock, productId);
    }

    @DeleteMapping("/redis")
    public void delete(@RequestParam("productId") Long productId){
        productService.deleteProduct(productId);
    }

}
