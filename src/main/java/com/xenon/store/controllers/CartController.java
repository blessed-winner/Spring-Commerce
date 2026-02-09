package com.xenon.store.controllers;

import com.xenon.store.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController("/carts")
public class CartController {

  @PostMapping
    public ResponseEntity<CartDto> createCart(
            @RequestBody CartDto cartDto,
            UriComponentsBuilder uriBuilder
  ){

  }
}
