package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "idUser", source = "userID")
    @Mapping(target = "id", source = "id")
    Product toProduct(ProductDTO productDto);

    @Mapping(target = "userID", source = "idUser")
    @Mapping(target = "id", source = "id")
    ProductDTO toProductDto(Product product);

    List<ProductDTO> toProductsDto(List<Product> products);

    @Mapping(source = "userID", target = "idUser")
    @Mapping(target = "id", source = "id")
    void updateProduct(@MappingTarget Product product, ProductDTO productDto);
}
