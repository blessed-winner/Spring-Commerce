package com.xenon.store.mappers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
}
