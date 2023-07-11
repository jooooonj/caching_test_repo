package com.example.order.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/redis/all")
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping("/redis/one")
    public Product getOne(){
        return productService.findProduct();
    }

    @PostMapping("/redis/modify")
    public void modify(@RequestParam("stock") int stock){

        productService.modifyStock(stock);
    }

}
