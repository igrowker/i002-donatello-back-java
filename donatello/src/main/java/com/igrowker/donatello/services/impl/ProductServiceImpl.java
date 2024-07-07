package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.ForbiddenException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProductMapper;
import com.igrowker.donatello.models.Customer;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.repositories.IProductRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    IAuthService authService;

    @Override
    public List<ProductDTO> getProducts() {
        return productMapper.toProductsDto(productRepository.findAll());
    }

    @Override
    public ProductDTO add(HttpHeaders headers, ProductDTO productDto) {
        Integer userId = authService.getLoguedUser(headers).getId();
        productDto.setUserID(userId);

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
    public ProductDTO update(HttpHeaders headers,Integer productId, ProductDTO productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found!"));

        Integer userId = authService.getLoguedUser(headers).getId();
        if(! product.getCustomUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot update someone else's product.");
        }

        product.setCustomUser(userRepository.getReferenceById(productDto.getUserID()));
        productMapper.updateProduct(product, productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO delete(HttpHeaders headers,Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found!"));

        Integer userId = authService.getLoguedUser(headers).getId();
        if(! product.getCustomUser().getId().equals(userId)){
            throw new ForbiddenException("The user cannot delete someone else's product.");
        }

        ProductDTO productDto = productMapper.toProductDto(product);
        productRepository.deleteById(productId);
        return productDto;
    }
}
