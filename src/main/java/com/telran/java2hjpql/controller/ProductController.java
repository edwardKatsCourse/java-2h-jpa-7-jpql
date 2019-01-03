package com.telran.java2hjpql.controller;

import com.telran.java2hjpql.dto.ProductRequest;
import com.telran.java2hjpql.entity.Product;
import com.telran.java2hjpql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products/create")
    public Product create(@RequestBody ProductRequest productRequest) {

        Product product = Product.builder()
                .productName(productRequest.getProduct())
                .sellerName(productRequest.getSeller())
                .createdOn(LocalDateTime.now())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        return product;
    }

    @GetMapping("/products/all")
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/products/product-names")
    public List<String> getAllProductNames() {
        return productRepository.getAllProductNames();
    }

    @PostMapping("/products/full-search")
    public List<Product> searchByAllParams(@RequestBody ProductRequest searchProductRequest) {
        //validate all fields are not null here!!!

        return productRepository.getAllProductsByAllParams(
                searchProductRequest.getProduct(),
                searchProductRequest.getPrice(),
                searchProductRequest.getSeller()
                );

    }

    @GetMapping("/products/search/by-name")
    public List<Product> getAllProductsByName(
            @RequestParam("name") String name,
            @RequestParam(value = "like", required = false) boolean like) {
        if (like) {
            return productRepository.getAllProductsByNameLike(name);
        }

        return productRepository.getAllProductsByProductName(name);
    }

    @PostMapping("/products/search/by-dynamic-parameters")
    public List<Product> searchByDynamicParams(@RequestBody ProductRequest productRequest) {
        return productRepository.searchByRequestObject(productRequest);
    }
}
