package com.xenon.store.mappers;

import com.xenon.store.dto.CartDto;
import com.xenon.store.dto.CartItemDto;
import com.xenon.store.dto.CartProductDto;
import com.xenon.store.entities.Cart;
import com.xenon.store.entities.CartItem;
import com.xenon.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice",expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);
    Cart toEntity(CartDto dto);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);

    CartProductDto toDto(Product product);
}
