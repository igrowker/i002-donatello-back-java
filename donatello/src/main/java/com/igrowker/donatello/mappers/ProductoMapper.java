package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProductoDto;
import com.igrowker.donatello.models.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "customUser", ignore = true)
    Producto toProducto(ProductoDto productoDto);

    @Mapping(target = "usuarioId", source = "producto.customUser.id")
    ProductoDto toProductoDto(Producto producto);

    List<ProductoDto> toProductosDtos(List<Producto> productos);

    void updateProducto(@MappingTarget Producto producto, ProductoDto productoDto);
}
