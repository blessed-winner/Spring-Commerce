package com.xenon.store.controllers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.mappers.ProductMapper;
import com.xenon.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public Iterable<ProductDto>getAllProducts(){
        return productRepository.findAll()
                                .stream()
                                .map(productMapper::toDto)
                                .toList();
    }
}
