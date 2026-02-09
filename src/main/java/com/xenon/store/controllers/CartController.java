package com.xenon.store.controllers;

import com.xenon.store.dto.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/carts")
public class CartController {
  @PostMapping
    public ResponseEntity<CartDto> createCart(){

  }
}
