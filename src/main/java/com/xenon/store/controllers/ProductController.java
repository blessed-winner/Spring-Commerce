package com.xenon.store.controllers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.entities.Product;
import com.xenon.store.mappers.ProductMapper;
import com.xenon.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public Iterable<ProductDto>getAllProducts(@RequestParam(defaultValue = "",name = "categoryId",required = false) String categoryId){
        return productRepository.findAll(Sort.by(categoryId))
                                .stream()
                                .map(productMapper::toDto)
                                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }
}
