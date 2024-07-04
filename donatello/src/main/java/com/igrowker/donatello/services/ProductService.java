package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    List<ProductDto> getProducts();

    ProductDto update(Integer productId, ProductDto productDto);

    ProductDto delete(Integer productId);
}
