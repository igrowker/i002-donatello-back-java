package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ProductoDto;
import com.igrowker.donatello.models.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "idUsuario", source = "usuarioId")
    Producto toProducto(ProductoDto productoDto);

    @Mapping(target = "usuarioId", source = "idUsuario")
    ProductoDto toProductoDto(Producto producto);

    List<ProductoDto> toProductosDtos(List<Producto> productos);

    @Mapping(source = "usuarioId", target = "idUsuario")
    void updateProducto(@MappingTarget Producto producto, ProductoDto productoDto);
}
