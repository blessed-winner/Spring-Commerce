package com.xenon.store.controllers;

import com.xenon.store.dto.AddItemToCartRequest;
import com.xenon.store.dto.CartDto;
import com.xenon.store.dto.CartItemDto;
import com.xenon.store.entities.Cart;
import com.xenon.store.entities.CartItem;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import com.xenon.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
  private CartRepository cartRepository;
  private CartMapper cartMapper;
  private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder){

        Cart saved = cartRepository.save(new Cart());
        CartDto result = cartMapper.toDto(saved);

        return ResponseEntity.created(
                uriBuilder.path("/carts/{id}").buildAndExpand(saved.getId()).toUri()
        ).body(result);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request
            ){
          var cart = cartRepository.getCartWithItems(cartId).orElse(null);
          if(cart == null){
              return ResponseEntity.notFound().build();
          }

          var product = productRepository.findById(request.getProductId()).orElse(null);
          if(product == null){
              return ResponseEntity.badRequest().build();
          }

          var existingItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(product.getId()))
                  .findFirst().orElse(null);

          CartItem cartItem;
          if(existingItem != null){
              existingItem.setQuantity(existingItem.getQuantity() + 1);
              cartItem = existingItem;
          } else {
              cartItem = new CartItem();
              cartItem.setProduct(product);
              cartItem.setQuantity(1);
              cartItem.setCart(cart);
              cart.getItems().add(cartItem);
          }

          cartRepository.save(cart);

          return ResponseEntity.status(HttpStatus.CREATED).body(cartMapper.toDto(cartItem));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok(cartMapper.toDto(cart));

    }
}
