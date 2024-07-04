package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ProductDto;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProductMapper;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.repositories.ProductRepository;
import com.igrowker.donatello.repositories.UserRepository;
import com.igrowker.donatello.services.ProductService;
import com.igrowker.donatello.validators.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductValidator productValidator;
    @Override
    public ProductDto save(ProductDto productDto) {
        productValidator.validate(productDto);
        Boolean existsProduct = productRepository.existsByName(productDto.getName());
        if (!existsProduct){
           Product product = productMapper.toProduct(productDto);
           product.setCustomUser(userRepository.getReferenceById(productDto.getUserId()));
           return productMapper.toProductDto(productRepository.save(product));
        }else {
            throw new ConflictException("The product exists!");
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        return productMapper.toProductsDto(productRepository.findAll());
    }

    @Override
    public ProductDto update(Integer productId, ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found!"));
        product.setCustomUser(userRepository.getReferenceById(productDto.getUserId()));
        productMapper.updateProduct(product, productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto delete(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product not found!"));
        ProductDto productDto = productMapper.toProductDto(product);
        productRepository.deleteById(productId);
        return productDto;
    }
}
