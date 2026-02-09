package com.xenon.store.mappers;

import com.xenon.store.dto.CartDto;
import com.xenon.store.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
    Cart toEntity(CartDto dto);
}
