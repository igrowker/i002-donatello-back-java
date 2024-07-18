package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ProductDTO;
import com.igrowker.donatello.exceptions.ConflictException;
import com.igrowker.donatello.exceptions.ForbiddenException;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ProductMapper;
import com.igrowker.donatello.models.Customer;
import com.igrowker.donatello.models.Product;
import com.igrowker.donatello.models.ProviderEntity;
import com.igrowker.donatello.repositories.IProductRepository;
import com.igrowker.donatello.repositories.IUserRepository;
import com.igrowker.donatello.services.IAuthService;
import com.igrowker.donatello.services.IProductService;
import com.igrowker.donatello.services.IProviderService;
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
    @Autowired
    IProviderService providerService;

    @Override
    public List<ProductDTO> getProducts(HttpHeaders headers) {
        Integer userId = authService.getLoguedUser(headers).getId();
        return providerService.findAllProductsByIdUser(userId);
    }

    @Override
    public ProductDTO add(HttpHeaders headers, ProductDTO productDto) {
        Boolean existsProduct = providerService.existProductInProvider(productDto);
        if (!existsProduct) {
            Product product = productMapper.toProduct(productDto);
            Product savedProduct = productRepository.save(product);
            providerService.addNewProduct(productDto.getProviderId(), savedProduct);
            ProductDTO productDTO = productMapper.toProductDto(savedProduct);
            return productDTO;
        } else {
            throw new ConflictException("The product exists!");
        }
    }

    private Product getProducById(Integer id){
       return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found!"));
    }
    @Override
    public ProductDTO update(HttpHeaders headers,Integer productId, ProductDTO productDto) {
        Product product = getProducById(productId);
        productDto.setId(productId);
        productDto.setProviderId(product.getProviderId());
        productMapper.updateProduct(product, productDto); // esta linea, toma el dto que viene sin los ids y los pasa a la entidad producto, si no se setean los id en el dto, se genera error..
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public ProductDTO delete(HttpHeaders headers,Integer productId) {
        Product product = getProducById(productId);
        ProductDTO productDto = productMapper.toProductDto(product);
        providerService.deleteProduct(productDto.getProviderId(), product);
        productRepository.deleteById(productId);
        return productDto;
    }
}
