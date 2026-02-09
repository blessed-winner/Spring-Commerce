package com.xenon.store.controllers;

import com.xenon.store.dto.CartDto;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController("/carts")
public class CartController {
  private CartRepository cartRepository;
  private CartMapper cartMapper;

  @PostMapping
    public ResponseEntity<CartDto> createCart(
            @RequestBody CartDto cartDto,
            UriComponentsBuilder uriBuilder
  ){
     var cart = cartMapper.toEntity(cartDto);
     cartRepository.save(cart);

     var cartCreateRequest = cartMapper.toDto(cart);
     var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartCreateRequest.getId()).toUri();

     return ResponseEntity.created(uri).body(cartCreateRequest);

  }
}
