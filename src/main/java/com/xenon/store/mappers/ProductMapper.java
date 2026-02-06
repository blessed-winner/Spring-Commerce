package com.xenon.store.mappers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id",target = "category_id")
    ProductDto toDto(Product product);
    Product toEntity(ProductDto dto);
}
