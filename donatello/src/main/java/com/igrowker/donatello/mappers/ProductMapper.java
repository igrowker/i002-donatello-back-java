package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProductDto;
import com.igrowker.donatello.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "idUser", source = "userId")
    Product toProduct(ProductDto productDto);

    @Mapping(target = "userId", source = "idUser")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductsDto(List<Product> products);

    @Mapping(source = "userId", target = "idUser")
    void updateProduct(@MappingTarget Product product, ProductDto productDto);
}
