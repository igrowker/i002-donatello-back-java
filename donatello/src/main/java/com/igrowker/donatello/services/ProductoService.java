package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductoDto;

import java.util.List;

public interface ProductoService {

    ProductoDto save(ProductoDto productoDto);

    List<ProductoDto> getProductos();

    ProductoDto update(Integer productoId, ProductoDto productoDto);

    ProductoDto delete(Integer productoId);
}
