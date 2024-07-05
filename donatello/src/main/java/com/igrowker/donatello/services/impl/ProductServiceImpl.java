package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProductMapper;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.repositories.IProductRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.toProductsDto(productRepository.findAll());
    }

    @Override
    public ProductDTO add(ProductDTO productDto) {
        Boolean existsProduct = productRepository.existsByName(productDto.getName());
        if (!existsProduct) {
            Product product = productMapper.toProduct(productDto);
            product.setCustomUser(userRepository.getReferenceById(productDto.getUserID()));
            return productMapper.toProductDto(productRepository.save(product));
        } else {
            throw new ConflictException("The product exists!");
        }
    }

    @Override
    public ProductDTO update(Integer productId, ProductDTO productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found!"));
        product.setCustomUser(userRepository.getReferenceById(productDto.getUserID()));
        productMapper.updateProduct(product, productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO delete(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found!"));
        ProductDTO productDto = productMapper.toProductDto(product);
        productRepository.deleteById(productId);
        return productDto;
    }
}
