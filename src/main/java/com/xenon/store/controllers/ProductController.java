package com.xenon.store.controllers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.entities.Category;
import com.xenon.store.entities.Product;
import com.xenon.store.mappers.ProductMapper;
import com.xenon.store.repositories.CategoryRepository;
import com.xenon.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public Iterable<ProductDto>getAllProducts(@RequestParam(required = false) Byte categoryId){
        if(categoryId != null){
            return productRepository.findByCategory_Id(categoryId)
                                    .stream()
                                    .map(productMapper::toDto)
                                    .toList();
        } else {
            return productRepository.findAll()
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder
    ){
        Category category = categoryRepository.findById(productDto.getCategory_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category not found"));
        var product = productMapper.toEntity(productDto);
        product.setCategory(category);

        productRepository.save(product);

        var productCreateRequest = productMapper.toDto(product);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(productCreateRequest);

    }
}
