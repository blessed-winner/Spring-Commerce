package com.xenon.store.controllers;

import com.xenon.store.dto.CartDto;
import com.xenon.store.entities.Cart;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
  private CartRepository cartRepository;
  private CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder) {

        Cart saved = cartRepository.save(new Cart());
        CartDto result = cartMapper.toDto(saved);

        return ResponseEntity.created(
                uriBuilder.path("/carts/{id}").buildAndExpand(saved.getId()).toUri()
        ).body(result);
    }
}
