package com.xenon.luxe.mappers;

import com.xenon.luxe.dto.CartDto;
import com.xenon.luxe.dto.CartItemDto;
import com.xenon.luxe.dto.CartProductDto;
import com.xenon.luxe.entities.Cart;
import com.xenon.luxe.entities.CartItem;
import com.xenon.luxe.entities.Product;
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
