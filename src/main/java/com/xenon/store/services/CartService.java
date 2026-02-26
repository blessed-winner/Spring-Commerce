package com.xenon.store.services;

import com.xenon.store.dto.CartDto;
import com.xenon.store.dto.CartItemDto;
import com.xenon.store.entities.Cart;
import com.xenon.store.exceptions.CartNotFoundException;
import com.xenon.store.exceptions.ProductNotFoundException;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import com.xenon.store.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {
    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;
   public CartDto createCart(){
       Cart saved = cartRepository.save(new Cart());
       return cartMapper.toDto(saved);
   }

   public CartItemDto addToCart(UUID cartId, Long productId){
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if (cart == null) {
           throw new CartNotFoundException();
       }

       var product = productRepository.findById(productId).orElse(null);
       if (product == null) {
           throw new ProductNotFoundException();
       }

       var cartItem = cart.addItem(product);

       cartRepository.save(cart);

       return cartMapper.toDto(cartItem);

   }
}
