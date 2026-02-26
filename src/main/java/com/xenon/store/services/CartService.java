package com.xenon.store.services;

import com.xenon.store.dto.CartDto;
import com.xenon.store.dto.CartItemDto;
import com.xenon.store.entities.Cart;
import com.xenon.store.exceptions.CartNotFoundException;
import com.xenon.store.exceptions.ProductNotFoundException;
import com.xenon.store.mappers.CartMapper;
import com.xenon.store.repositories.CartRepository;
import com.xenon.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
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

   public CartDto getCart(UUID cartId){
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if (cart == null) {
           throw new CartNotFoundException();
       }

       return cartMapper.toDto(cart);
   }

   public CartItemDto updateItem(UUID cartId, Long productId, Integer quantity){
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if(cart == null){
           throw new CartNotFoundException();
       }
       var existingItem = cart.getItem(productId);
       if(existingItem == null){
          throw new  ProductNotFoundException();
       }
       existingItem.setQuantity(quantity);
       cartRepository.save(cart);

       return cartMapper.toDto(existingItem);
   }

   public void removeItem(UUID cartId, Long productId){
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if(cart == null){
           throw new CartNotFoundException();
       }

       cart.removeItem(productId);

       cartRepository.save(cart);
   }

   public void clearCart(UUID cartId){
       var cart = cartRepository.getCartWithItems(cartId).orElse(null);
       if(cart == null){
           throw new CartNotFoundException();
       }
       cart.clear();
       cartRepository.save(cart);
   }
}
