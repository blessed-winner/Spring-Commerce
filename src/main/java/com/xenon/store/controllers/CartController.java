package com.xenon.store.controllers;

import com.xenon.store.dto.AddItemToCartRequest;
import com.xenon.store.dto.CartDto;
import com.xenon.store.dto.CartItemDto;
import com.xenon.store.dto.UpdateCartItemRequest;
import com.xenon.store.entities.Cart;
import com.xenon.store.entities.CartItem;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import com.xenon.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
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
            UriComponentsBuilder uriBuilder) {

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
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartMapper.toDto(cartItem));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if(cart == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                 Map.of("error","Cart not found")
           );
       }
        var existingItem = cart.getItem(productId);
       if(existingItem == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   Map.of("error", "Product was not found in the cart")
           );
        }
           existingItem.setQuantity(request.getQuantity());
           cartRepository.save(cart);

           return ResponseEntity.ok(cartMapper.toDto(existingItem));
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error","Cart not found")
            );
        }

        cart.removeItem(productId);

        cartRepository.save(cart);

        return ResponseEntity.noContent().build();
    }
}
