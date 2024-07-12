package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ProductDTO;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getProducts(HttpHeaders headers);

    ProductDTO add(HttpHeaders headers, ProductDTO productDto);

    ProductDTO update(HttpHeaders headers,Integer productId, ProductDTO productDto);

    ProductDTO delete(HttpHeaders headers,Integer productId);
}
