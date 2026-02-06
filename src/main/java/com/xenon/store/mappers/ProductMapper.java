package com.xenon.store.mappers;

import com.xenon.store.dto.ProductDto;
import com.xenon.store.dto.UpdateProductRequest;
import com.xenon.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id",target = "category_id")
    ProductDto toDto(Product product);

    @Mapping(target = "category",ignore = true)
    Product toEntity(ProductDto dto);

    void update(UpdateProductRequest request, @MappingTarget Product product );
}
