package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getProducts();

    ProductDTO add(ProductDTO productDto);

    ProductDTO update(Integer productId, ProductDTO productDto);

    ProductDTO delete(Integer productId);
}
