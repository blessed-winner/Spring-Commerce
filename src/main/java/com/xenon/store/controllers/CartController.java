package com.xenon.store.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/carts")
public class CartController {
  @PostMapping
    public ResponseEntity<> createCart(){

  }
}
